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

package cherry.example.web.validation.ex40;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ValidationEx40Form {

	@Valid()
	private ValidationEx40SubForm subform;

	@Valid()
	private List<ValidationEx40SubForm> list1;

	@Valid()
	private List<List2Property> list2;

	@Valid()
	private Map<String, ValidationEx40SubForm> map1;

	@Valid()
	private Map<String, Map2Property> map2;

	@Getter
	@Setter
	@EqualsAndHashCode
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class List2Property {
		@Valid()
		private List<ValidationEx40SubForm> v;
	}

	@Getter
	@Setter
	@EqualsAndHashCode
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Map2Property {
		@Valid()
		private Map<String, ValidationEx40SubForm> v;
	}

}
