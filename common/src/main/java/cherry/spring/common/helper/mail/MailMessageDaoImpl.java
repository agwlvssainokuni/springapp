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

package cherry.spring.common.helper.mail;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MailMessageDaoImpl implements MailMessageDao, InitializingBean {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcOperations;

	@Value("cherry/spring/common/helper/mail/MailMessageDaoImpl_findTemplate.sql")
	private Resource resourceFindTemplate;

	@Value("cherry/spring/common/helper/mail/MailMessageDaoImpl_findAddresses.sql")
	private Resource resourceFindAddresses;

	@Value("UTF-8")
	private Charset charset;

	private String sqlFindTemplate;

	private String sqlFindAddresses;

	@Override
	public void afterPropertiesSet() throws IOException {
		sqlFindTemplate = resourceToString(resourceFindTemplate, charset);
		sqlFindAddresses = resourceToString(resourceFindAddresses, charset);
	}

	@Override
	public MailTemplateDto findTemplate(String name, Locale locale) {

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("name", name);
		paramMap.put("locale", locale.toString());

		return namedParameterJdbcOperations.queryForObject(sqlFindTemplate,
				paramMap, new BeanPropertyRowMapper<MailTemplateDto>(
						MailTemplateDto.class));
	}

	@Override
	public List<MailTemplateAddressDto> findAddresses(String name) {

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("name", name);

		return namedParameterJdbcOperations.query(sqlFindAddresses, paramMap,
				new BeanPropertyRowMapper<MailTemplateAddressDto>(
						MailTemplateAddressDto.class));

	}

	private String resourceToString(Resource res, Charset cs)
			throws IOException {
		try (StringWriter writer = new StringWriter();
				InputStream in = res.getInputStream();
				Reader reader = new InputStreamReader(in, cs)) {
			int len;
			char[] buff = new char[4096];
			while ((len = reader.read(buff, 0, buff.length)) >= 0) {
				writer.write(buff, 0, len);
			}
			return writer.toString();
		}
	}

}
