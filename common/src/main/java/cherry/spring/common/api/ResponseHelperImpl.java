/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.spring.common.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ResponseHelperImpl implements ResponseHelper, MessageSourceAware {

	private MessageSource messageSource;

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public <T> Response<T> createResponse(StatusCode statusCode, T result) {
		Response<T> response = new Response<>();
		response.setStatusCode(statusCode.code());
		response.setResult(result);
		return response;
	}

	@Override
	public <T> Response<T> createResponse(StatusCode statusCode, BindingResult binding) {
		Response<T> response = new Response<>();
		response.setStatusCode(statusCode.code());
		response.setDescription(getMessageList(binding.getAllErrors()));
		return response;
	}

	@Override
	public <T> Response<T> createResponse(StatusCode statusCode, List<? extends MessageSourceResolvable> messages) {
		Response<T> response = new Response<>();
		response.setStatusCode(statusCode.code());
		response.setDescription(getMessageList(messages));
		return response;
	}

	private List<Response.Item> getMessageList(List<? extends MessageSourceResolvable> messages) {
		Locale locale = LocaleContextHolder.getLocale();
		List<Response.Item> list = new ArrayList<>(messages.size());
		for (MessageSourceResolvable resolvable : messages) {
			String code = resolvable.getCodes()[resolvable.getCodes().length - 1];
			String message = messageSource.getMessage(resolvable, locale);
			list.add(new Response.Item(code, message));
		}
		return list;
	}

}
