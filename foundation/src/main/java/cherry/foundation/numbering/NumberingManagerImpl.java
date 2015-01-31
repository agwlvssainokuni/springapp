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

package cherry.foundation.numbering;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.type.Code;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class NumberingManagerImpl implements NumberingManager, InitializingBean {

	private NumberingStore numberingStore;

	private long cacheSize;

	private long cacheTimeoutSec;

	private LoadingCache<String, NumberingDefinition> definitionCache;

	public void setNumberingStore(NumberingStore numberingStore) {
		this.numberingStore = numberingStore;
	}

	public void setCacheSize(long cacheSize) {
		this.cacheSize = cacheSize;
	}

	public void setCacheTimeoutSec(long cacheTimeoutSec) {
		this.cacheTimeoutSec = cacheTimeoutSec;
	}

	@Override
	public void afterPropertiesSet() {
		definitionCache = CacheBuilder.newBuilder().maximumSize(cacheSize)
				.expireAfterAccess(cacheTimeoutSec, TimeUnit.SECONDS)
				.build(new CacheLoader<String, NumberingDefinition>() {
					@Override
					public NumberingDefinition load(String key) {
						return numberingStore.getDefinition(key);
					}
				});
	}

	@Transactional
	@Override
	public <T extends Code<String>> String issueAsString(T numberEnum) {
		return issueAsString(numberEnum.code());
	}

	@Transactional
	@Override
	public <T extends Code<String>> String[] issueAsString(T numberEnum, int count) {
		return issueAsString(numberEnum.code(), count);
	}

	@Transactional
	@Override
	public String issueAsString(String numberName) {
		String[] values = issueAsString(numberName, 1);
		return values[0];
	}

	@Transactional
	@Override
	public String[] issueAsString(String numberName, int count) {
		checkArgument(count > 0, "count must be > 0");
		try {
			NumberingDefinition def = definitionCache.get(numberName);
			MessageFormat fmt = new MessageFormat(def.getTemplate());
			long current = numberingStore.loadCurrent(numberName);
			String[] values = new String[count];
			for (int i = 1; i <= count; i++) {
				long val = current + i;
				checkState(val >= def.getMinValue(), "{0} must be >= {1}", numberName, def.getMinValue());
				checkState(val <= def.getMaxValue(), "{0} must be <= {1}", numberName, def.getMaxValue());
				values[i] = fmt.format(val);
			}
			numberingStore.saveCurrent(numberName, current + count);
			return values;
		} catch (ExecutionException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Transactional
	@Override
	public <T extends Code<String>> long issueAsLong(T numberEnum) {
		return issueAsLong(numberEnum.code());
	}

	@Transactional
	@Override
	public <T extends Code<String>> long[] issueAsLong(T numberEnum, int count) {
		return issueAsLong(numberEnum.code(), count);
	}

	@Transactional
	@Override
	public long issueAsLong(String numberName) {
		long[] values = issueAsLong(numberName, 1);
		return values[0];
	}

	@Transactional
	@Override
	public long[] issueAsLong(String numberName, int count) {
		checkArgument(count > 0, "count must be > 0");
		try {
			NumberingDefinition def = definitionCache.get(numberName);
			long current = numberingStore.loadCurrent(numberName);
			long[] values = new long[count];
			for (int i = 1; i <= count; i++) {
				long val = current + i;
				checkState(val >= def.getMinValue(), "{0} must be >= {1}", numberName, def.getMinValue());
				checkState(val <= def.getMaxValue(), "{0} must be <= {1}", numberName, def.getMaxValue());
				values[i] = val;
			}
			numberingStore.saveCurrent(numberName, current + count);
			return values;
		} catch (ExecutionException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
