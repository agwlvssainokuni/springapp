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

package cherry.spring.common.format;

import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;

public abstract class ReadablePartialTo<T extends ReadablePartial> implements
		RangeTo<T> {

	private final T original;

	private final ReadablePeriod offset;

	public ReadablePartialTo(T original, ReadablePeriod offset) {
		this.original = original;
		this.offset = offset;
	}

	@Override
	public T getOriginal() {
		return original;
	}

	@Override
	public ReadablePeriod getOffset() {
		return offset;
	}

}
