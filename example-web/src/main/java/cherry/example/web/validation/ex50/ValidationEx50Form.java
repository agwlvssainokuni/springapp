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

package cherry.example.web.validation.ex50;

import javax.validation.groups.Default;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;

import cherry.foundation.validator.groups.G1;
import cherry.foundation.validator.groups.G2;
import cherry.foundation.validator.groups.G3;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ValidationEx50Form {

	@NotEmpty(groups = { Default.class })
	private String text;

	@NotEmpty(groups = { Default.class, G1.class })
	private String text1;

	@NotEmpty(groups = { Default.class, G2.class })
	private String text2;

	@NotEmpty(groups = { Default.class, G3.class })
	private String text3;

	@NotEmpty(groups = { Default.class, G1.class, G2.class })
	private String text12;

	@NotEmpty(groups = { Default.class, G1.class, G2.class, G3.class })
	private String text123;

}
