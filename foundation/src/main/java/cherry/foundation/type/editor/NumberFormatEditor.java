/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.foundation.type.editor;

import java.beans.PropertyEditorSupport;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberFormatEditor extends PropertyEditorSupport {

	private String originalValue;

	@Override
	public void setAsText(String text) {
		setValue(text == null ? null : new DecimalFormat(text));
		originalValue = text;
	}

	@Override
	public String getAsText() {
		NumberFormat nf = (NumberFormat) getValue();
		if (nf == null) {
			return "";
		}
		return originalValue;
	}

}
