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

package cherry.example.web.applied.ex40;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.context.MessageSourceResolvable;

import cherry.foundation.logicalerror.LogicalErrorUtil;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AppliedEx42Form {

	@NotNull()
	@Valid()
	private Map<Integer, AppliedEx41SubForm> item;

	public static String getItemPropName(int rownum, AppliedEx41SubFormBase.Prop prop) {
		return new StringBuilder().append("item[").append(rownum).append("].").append(prop.getName()).toString();
	}

	public static MessageSourceResolvable resolveItemProp(int rownum, AppliedEx41SubFormBase.Prop prop) {
		String formName = UPPER_CAMEL.to(LOWER_CAMEL, AppliedEx42Form.class.getSimpleName());
		return LogicalErrorUtil.resolve(new StringBuilder(formName).append(".").append(getItemPropName(rownum, prop))
				.toString());
	}

}
