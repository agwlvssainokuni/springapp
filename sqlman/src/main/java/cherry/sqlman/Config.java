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

package cherry.sqlman;

import java.nio.charset.Charset;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component()
public class Config {

	@Value("${sqlman.paginator.pageSize}")
	private long paginatorDefaultPageSize;

	@Value("${sqlman.search.defaultFromDays}")
	private Integer searchDefaultFromDays;

	@Value("${sqlman.export.contentType}")
	private String exportContentType;

	@Value("${sqlman.export.charset}")
	private Charset exportCharset;

	@Value("${sqlman.export.filename}")
	private String exportFilename;

	@Value("${sqlman.import.charset}")
	private Charset importCharset;

}
