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

import java.util.List;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.BindingResult;

import cherry.foundation.util.MessageSourceUtil;

public class ResponseFactory {

	public static <T> Response<T> createResponse(StatusCode statusCode, T result) {
		Response<T> response = new Response<>();
		response.setStatusCode(statusCode.getValue());
		response.setResult(result);
		return response;
	}

	public static <T> Response<T> createResponse(StatusCode statusCode, BindingResult binding) {
		Response<T> response = new Response<>();
		response.setStatusCode(statusCode.getValue());
		response.setDescription(MessageSourceUtil.getMessageList(binding));
		return response;
	}

	public static <T> Response<T> createResponse(StatusCode statusCode, List<MessageSourceResolvable> messages) {
		Response<T> response = new Response<>();
		response.setStatusCode(statusCode.getValue());
		response.setDescription(MessageSourceUtil.getMessageList(messages));
		return response;
	}

}
