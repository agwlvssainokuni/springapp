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

package cherry.foundation.validator;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;

public interface DataBinderHelper {

	BindingResult bindAndValidate(Object target, PropertyValues pvs);

	BindingResult bindAndValidate(Object target, String objectName, PropertyValues pvs);

	List<String> resolveAllMessage(BindingResult binding, Locale locale);

	List<String> resolveGlobalMessage(BindingResult binding, Locale locale);

	List<String> resolveFieldMessage(BindingResult binding, Locale locale);

	List<String> resolveFieldMessage(BindingResult binding, String field, Locale locale);

}
