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

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.DefaultMessageSourceResolvable;

public class DeindexMessageSource implements MessageSource {

	private final Pattern pattern = Pattern.compile("\\[([._0-9a-z]+)\\]", Pattern.CASE_INSENSITIVE);

	private final Pattern number = Pattern.compile("[0-9]+");

	private final String replacement = "[]";

	private MessageSource messageSource;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		Pair<String, List<Object>> p = parse(code);
		return messageSource.getMessage(p.getLeft(), getArgs(p.getRight(), args), defaultMessage, locale);
	}

	@Override
	public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
		Pair<String, List<Object>> p = parse(code);
		return messageSource.getMessage(p.getLeft(), getArgs(p.getRight(), args), locale);
	}

	@Override
	public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
		List<String> codes = new ArrayList<>(resolvable.getCodes().length);
		List<Object> params = new ArrayList<>();
		for (String c : resolvable.getCodes()) {
			Pair<String, List<Object>> p = parse(c);
			codes.add(p.getLeft());
			if (params.size() < p.getRight().size()) {
				params = p.getRight();
			}
		}
		MessageSourceResolvable r = new DefaultMessageSourceResolvable(codes.toArray(new String[codes.size()]),
				getArgs(params, resolvable.getArguments()), resolvable.getDefaultMessage());
		return messageSource.getMessage(r, locale);
	}

	private Pair<String, List<Object>> parse(String code) {

		List<Object> params = new ArrayList<>();
		Matcher m = pattern.matcher(code);
		boolean result = m.find();
		if (!result) {
			return Pair.of(code, params);
		}

		StringBuffer sb = new StringBuffer();
		do {
			String s = m.group(1);
			if (number.matcher(s).matches()) {
				params.add(Integer.parseInt(s));
			} else {
				params.add(new DefaultMessageSourceResolvable(new String[] { s }, s));
			}
			m.appendReplacement(sb, replacement);
			result = m.find();
		} while (result);
		m.appendTail(sb);
		return Pair.of(sb.toString(), params);
	}

	private Object[] getArgs(List<Object> params, Object[] args) {
		List<Object> list = new ArrayList<>(params.size() + args.length);
		list.addAll(params);
		for (Object a : args) {
			list.add(a);
		}
		return list.toArray();
	}

}
