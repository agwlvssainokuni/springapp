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

package cherry.example.web.basic.ex60;

import static cherry.example.web.PathDef.VIEW_BASIC_EX60_START;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.example.db.gen.query.BExTbl1;
import cherry.example.web.Config;
import cherry.example.web.LogicalError;
import cherry.example.web.SortOrder;
import cherry.example.web.SortParam;
import cherry.example.web.basic.ex50.SortBy;
import cherry.example.web.util.ModelAndViewBuilder;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.goods.paginate.PagedList;

@Controller
public class Ex60ControllerImpl implements Ex60Controller {

	@Autowired
	private Ex60Service service;

	@Autowired
	private Config config;

	@Override
	public ModelAndView init(String redir, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request, SessionStatus status) {

		status.setComplete();

		return ModelAndViewBuilder.redirect(redirectOnInit(redir)).build();
	}

	@Override
	public ModelAndView start(Ex60Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		form.setPno(0L);
		if (form.getPsz() <= 0L) {
			form.setPsz(config.getDefaultPageSize());
		}

		adjustSortCondition(form);

		return renderStartView().build();
	}

	@Override
	public ModelAndView execute(Ex60Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return renderStartView().build();
		}

		if (form.getPno() <= 0L) {
			form.setPno(0L);
		}
		if (form.getPsz() <= 0L) {
			form.setPsz(config.getDefaultPageSize());
		}

		adjustSortCondition(form);

		PagedList<BExTbl1> pagedList = service.search(form);
		if (pagedList.getPageSet().getTotalCount() <= 0L) {
			LogicalErrorUtil.rejectOnSearchResultEmpty(binding);
			return renderStartView().build();
		}

		Ex60to61Form f = new Ex60to61Form();
		f.setItem(createForm(pagedList.getList()));

		return renderStartView().addObject(pagedList).addObject(f).build();
	}

	@Override
	public ModelAndView download(Ex60Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, HttpServletResponse response) {

		if (hasErrors(form, binding)) {
			return renderStartView().build();
		}

		adjustSortCondition(form);
		service.downloadXlsx(form, response);

		return null;
	}

	private ModelAndViewBuilder renderStartView() {
		return ModelAndViewBuilder.withViewname(VIEW_BASIC_EX60_START);
	}

	private UriComponents redirectOnInit(String redir) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(Ex60Controller.class).start(null, null, null, null, null, null)).build();
		}
	}

	private boolean hasErrors(Ex60Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック
		if (form.getDtFrom() != null && form.getDtTo() != null) {
			if (form.getDtFrom().isAfter(form.getDtTo())) {
				LogicalErrorUtil.rejectValue(binding, "dtFrom", LogicalError.RangeFromTo,
						LogicalErrorUtil.resolve("ex40Form.dtFrom"), LogicalErrorUtil.resolve("ex40Form.dtTo"));
			}
		}
		if (form.getTmFrom() != null && form.getTmTo() != null) {
			if (form.getTmFrom().isAfter(form.getTmTo())) {
				LogicalErrorUtil.rejectValue(binding, "tmFrom", LogicalError.RangeFromTo,
						LogicalErrorUtil.resolve("ex40Form.tmFrom"), LogicalErrorUtil.resolve("ex40Form.tmTo"));
			}
		}
		if (form.getDtmFromD() == null && form.getDtmFromT() != null) {
			LogicalErrorUtil.rejectValue(binding, "dtmFromD", LogicalError.RequiredWhen,
					LogicalErrorUtil.resolve("ex40Form.dtmFromD"), LogicalErrorUtil.resolve("ex40Form.dtmFromT"));
		}
		if (form.getDtmToD() == null && form.getDtmToT() != null) {
			LogicalErrorUtil.rejectValue(binding, "dtmToD", LogicalError.RequiredWhen,
					LogicalErrorUtil.resolve("ex40Form.dtmToD"), LogicalErrorUtil.resolve("ex40Form.dtmToT"));
		}
		if (form.getDtmFromD() != null && form.getDtmFromT() != null && form.getDtmToD() != null
				&& form.getDtmToT() != null) {
			LocalDateTime dtmFrom = form.getDtmFromD().toLocalDateTime(form.getDtmFromT());
			LocalDateTime dtmTo = form.getDtmToD().toLocalDateTime(form.getDtmToT());
			if (dtmFrom.isAfter(dtmTo)) {
				LogicalErrorUtil.rejectValue(binding, "dtmFromD", LogicalError.RangeFromTo,
						LogicalErrorUtil.resolve("ex40Form.dtmFromD"), LogicalErrorUtil.resolve("ex40Form.dtmToD"));
			}
		}

		if (binding.hasErrors()) {
			return true;
		}

		// 整合性チェック

		return false;
	}

	private void adjustSortCondition(Ex60Form form) {

		if (form.getSort1() == null) {
			form.setSort1(new SortParam());
		}
		if (form.getSort1().getBy() == null) {
			form.getSort1().setBy(SortBy.ID.code());
		}
		if (form.getSort1().getOrder() == null) {
			form.getSort1().setOrder(SortOrder.ASC);
		}

		if (form.getSort2() == null) {
			form.setSort2(new SortParam());
		}
		if (form.getSort2().getBy() == null) {
			form.getSort2().setBy(SortBy.TEXT10.code());
		}
		if (form.getSort2().getOrder() == null) {
			form.getSort2().setOrder(SortOrder.ASC);
		}
	}

	private List<Ex60to61SubForm> createForm(List<BExTbl1> list) {
		List<Ex60to61SubForm> l = new ArrayList<>(list.size());
		for (BExTbl1 t : list) {
			Ex60to61SubForm f = new Ex60to61SubForm();
			f.setId(t.getId());
			f.setChecked(Boolean.FALSE);
			l.add(f);
		}
		return l;
	}

}
