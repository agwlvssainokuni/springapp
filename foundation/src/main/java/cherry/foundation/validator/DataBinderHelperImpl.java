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

package cherry.foundation.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.PropertyValues;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;

public class DataBinderHelperImpl implements DataBinderHelper, MessageSourceAware {

	private ConversionService conversionService;

	private Validator validator;

	private MessageSource messageSource;

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public BindingResult bindAndValidate(Object target, PropertyValues pvs) {
		return doBindAndValidate(new WebDataBinder(target), pvs);
	}

	@Override
	public BindingResult bindAndValidate(Object target, String objectName, PropertyValues pvs) {
		return doBindAndValidate(new WebDataBinder(target, objectName), pvs);
	}

	@Override
	public List<String> resolveAllMessage(BindingResult binding, Locale locale) {
		return doResolveMessage(binding.getAllErrors(), locale);
	}

	@Override
	public List<String> resolveGlobalMessage(BindingResult binding, Locale locale) {
		return doResolveMessage(binding.getGlobalErrors(), locale);
	}

	@Override
	public List<String> resolveFieldMessage(BindingResult binding, Locale locale) {
		return doResolveMessage(binding.getFieldErrors(), locale);
	}

	@Override
	public List<String> resolveFieldMessage(BindingResult binding, String field, Locale locale) {
		return doResolveMessage(binding.getFieldErrors(field), locale);
	}

	private BindingResult doBindAndValidate(WebDataBinder binder, PropertyValues pvs) {
		binder.setConversionService(conversionService);
		binder.addValidators(validator);
		binder.bind(pvs);
		binder.validate();
		return binder.getBindingResult();
	}

	private <E extends MessageSourceResolvable> List<String> doResolveMessage(List<E> messages, Locale locale) {
		List<String> list = new ArrayList<>(messages.size());
		for (MessageSourceResolvable msg : messages) {
			list.add(messageSource.getMessage(msg, locale));
		}
		return list;
	}

}
