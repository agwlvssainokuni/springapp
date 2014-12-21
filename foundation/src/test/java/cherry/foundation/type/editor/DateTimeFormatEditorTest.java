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

package cherry.foundation.type.editor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DateTimeFormatEditorTest {

	@Test
	public void testGetAsText_00() {
		DateTimeFormatEditor editor = new DateTimeFormatEditor();
		editor.setAsText(null);
		assertEquals("", editor.getAsText());
	}

	@Test
	public void testGetAsText_01() {
		DateTimeFormatEditor editor = new DateTimeFormatEditor();
		editor.setAsText("yyyy/MM/dd");
		assertEquals("yyyy/MM/dd", editor.getAsText());
	}

}
