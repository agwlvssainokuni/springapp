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

package cherry.foundation.springmvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.DefaultMessageSourceResolvable;

public class DeindexMessageSource implements MessageSource {

	private final Pattern pattern = Pattern.compile("\\[([._0-9a-z]+)\\]", Pattern.CASE_INSENSITIVE);

	private MessageSource messageSource;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		return messageSource.getMessage(parse2(code, args, defaultMessage), locale);
	}

	@Override
	public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
		return messageSource.getMessage(parse2(code, args, null), locale);
	}

	@Override
	public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
		for (String code : resolvable.getCodes()) {
			try {
				return messageSource.getMessage(
						parse2(code, resolvable.getArguments(), resolvable.getDefaultMessage()), locale);
			} catch (NoSuchMessageException ex) {
				// NOTHING
			}
		}
		throw new NoSuchMessageException(resolvable.getCodes().length <= 0 ? null : resolvable.getCodes()[0], locale);
	}

	private MessageSourceResolvable parse2(String code, Object[] args, String defaultMessage) {

		List<MessageSourceResolvable> params = new ArrayList<>();

		Matcher m = pattern.matcher(code);
		boolean result = m.find();
		if (!result) {
			return new DefaultMessageSourceResolvable(new String[] { code }, getArgs(params, args), defaultMessage);
		}

		StringBuffer sb = new StringBuffer();
		do {
			String s = m.group(1);
			params.add(create(sb.toString(), s));
			m.appendReplacement(sb, "[]");
			result = m.find();
		} while (result);
		m.appendTail(sb);

		return new DefaultMessageSourceResolvable(new String[] { code, sb.toString() }, getArgs(params, args),
				defaultMessage);
	}

	private MessageSourceResolvable create(String prefix, String name) {
		return new DefaultMessageSourceResolvable(new String[] { name, prefix + "." + name, prefix + "[]." + name },
				name);
	}

	private Object[] getArgs(List<MessageSourceResolvable> params, Object[] args) {
		List<Object> list = new ArrayList<>(params.size() + args.length);
		list.addAll(params);
		for (Object a : args) {
			list.add(a);
		}
		return list.toArray();
	}

}
