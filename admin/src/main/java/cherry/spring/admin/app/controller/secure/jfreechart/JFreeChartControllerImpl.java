/*
 * Copyright 2014 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cherry.spring.admin.app.controller.secure.jfreechart;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cherry.spring.common.lib.csv.CsvParser;
import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;

@Controller
@RequestMapping(JFreeChartController.URI_PATH)
public class JFreeChartControllerImpl implements JFreeChartController {

	public static final String VIEW_PATH = "secure/jfreechart/index";

	private Log log = LogFactory.getLog(getClass());

	@Value("${admin.app.jfreechart.charset}")
	private Charset charset;

	@Value("${admin.app.jfreechart.contentType}")
	private String contentType;

	@Value("${admin.app.jfreechart.xLabel}")
	private String xLabel;

	@Value("${admin.app.jfreechart.yLabel}")
	private String yLabel;

	@Value("${admin.app.jfreechart.imageWidth}")
	private Integer imageWidth;

	@Value("${admin.app.jfreechart.imageHeight}")
	private Integer imageHeight;

	@RequestMapping()
	@Override
	public ModelAndView index(Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(new JFreeChartForm());
		return mav;
	}

	@RequestMapping(URI_PATH_REQ)
	@Override
	public ModelAndView request(@Validated JFreeChartForm form,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request, HttpServletResponse response) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(form);
			return mav;
		}

		response.setContentType(contentType);

		try {
			CategoryDataset dataset;
			try (InputStream in = form.getFile().getInputStream()) {
				dataset = loadCategoryDataset(in);
			}
			JFreeChart chart = ChartFactory.createLineChart(form.getFile()
					.getOriginalFilename(), xLabel, yLabel, dataset);
			try (OutputStream out = response.getOutputStream()) {
				ChartUtilities.writeChartAsPNG(out, chart, imageWidth,
						imageHeight);
			}
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}

		return null;
	}

	private CategoryDataset loadCategoryDataset(InputStream in)
			throws IOException {

		@SuppressWarnings("resource")
		CsvParser csv = new CsvParser(new InputStreamReader(in, charset));

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		String[] header = csv.read();
		if (header == null) {
			return dataset;
		}

		NumberFormat format = NumberFormat.getInstance();
		String[] record;
		while ((record = csv.read()) != null) {
			for (int i = 1; i < record.length && i < header.length; i++) {
				try {
					dataset.addValue(format.parse(record[i]), header[i],
							record[0]);
				} catch (ParseException ex) {
					log.debug(ex, "Invalid format {0}", record[i]);
				}
			}
		}

		return dataset;
	}

}
