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

package cherry.spring.admin.app.controller.jfreechart;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import cherry.spring.admin.app.controller.BaseForm;

public class JFreeChartForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "jFreeChartForm";
	public static final String PREFIX = NAME + ".";

	public static final String FILE = "file";

	@NotNull
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
