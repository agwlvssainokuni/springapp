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

package cherry.example.web;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Getter
@Component("webConfig")
public class Config {

	@Value("${web.app.defaultPageSize}")
	private long defaultPageSize;

	@Value("${web.app.historySize}")
	private int historySize;

	@Value("${web.validation.ex40.list1}")
	private Resource validationEx40list1;

	@Value("${web.validation.ex40.list2}")
	private Resource validationEx40list2;

	@Value("${web.validation.ex40.map1}")
	private Resource validationEx40map1;

	@Value("${web.validation.ex40.map2}")
	private Resource validationEx40map2;

}
