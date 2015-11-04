/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.example.web.applied.ex90;

import static cherry.example.web.util.ModelAndViewBuilder.redirect;
import static cherry.example.web.util.ModelAndViewBuilder.withViewname;
import static cherry.example.web.util.ModelAndViewBuilder.withoutView;
import static java.util.Arrays.asList;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.example.common.io.TempDirRepository;
import cherry.example.web.LogicalError;
import cherry.example.web.applied.ex90.AppliedEx90FormBase.Prop;
import cherry.example.web.util.ViewNameUtil;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;

import com.google.common.io.ByteStreams;
import com.ibm.icu.text.MessageFormat;

@Controller
public class AppliedEx90ControllerImpl implements AppliedEx90Controller {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private AppliedEx90Service service;

	@Autowired
	private TempDirRepository tempDirRepository;

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(AppliedEx90Controller.class).start(null,
			null, null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return redirect(redirectOnInit(redirTo)).build();
	}

	@Override
	public ModelAndView start(AppliedEx90Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView confirm(AppliedEx90Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		Pair<String, List<String>> temp = createTempFile(asList(form.getFile()));
		form.setDirname(temp.getLeft());
		form.setNumOfFile(temp.getRight().size());
		form.setOriginalFilename(form.getFile().getOriginalFilename());

		return withoutView().build();
	}

	@Override
	public ModelAndView back(AppliedEx90Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		deleteTempDir(form.getDirname());
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView execute(AppliedEx90Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		try {

			if (hasErrors(form, binding)) {
				return withViewname(viewnameOfStart).build();
			}

			if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
				LogicalErrorUtil.rejectOnOneTimeTokenError(binding);
				return withViewname(viewnameOfStart).build();
			}

			List<AppliedEx90ResultDto> result = service.load(form, getTempFile(form.getDirname(), form.getNumOfFile()));

			return withoutView().addObject(result).build();

		} finally {
			deleteTempDir(form.getDirname());
		}
	}

	private UriComponents redirectOnInit(String redir) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(AppliedEx90Controller.class).start(null, null, null, null, null, null)).build();
		}
	}

	private boolean hasErrors(AppliedEx90Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック
		if (form.getDt() == null && form.getTm() != null) {
			LogicalErrorUtil.rejectValue(binding, Prop.Dt.getName(), LogicalError.RequiredWhen, Prop.Dt.resolve(),
					Prop.Tm.resolve());
		}

		if (binding.hasErrors()) {
			return true;
		}

		// 整合性チェック

		if (binding.hasErrors()) {
			return true;
		}

		return false;
	}

	private String createFilename(int num) {
		return MessageFormat.format("ex90_{0}.file", num);
	}

	private Pair<String, List<String>> createTempFile(List<MultipartFile> file) {
		try {

			String dirname = tempDirRepository.createTempDir();
			File dir = tempDirRepository.getTempDir(dirname);

			List<String> list = new ArrayList<>(file.size());
			for (int num = 0; num < file.size(); num++) {
				String filename = createFilename(num);
				try (InputStream in = file.get(num).getInputStream();
						OutputStream out = new FileOutputStream(new File(dir, filename))) {
					ByteStreams.copy(in, out);
				}
				list.add(filename);
			}

			return Pair.of(dirname, list);

		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private List<File> getTempFile(String dirname, int numOfFile) {

		File dir = tempDirRepository.getTempDir(dirname);

		List<File> list = new ArrayList<>(numOfFile);
		for (int num = 0; num < numOfFile; num++) {
			String filename = createFilename(num);
			list.add(new File(dir, filename));
		}

		return list;
	}

	private void deleteTempDir(String dirname) {
		if (StringUtils.isNotEmpty(dirname)) {
			tempDirRepository.deleteTempDir(dirname);
		}
	}

}
