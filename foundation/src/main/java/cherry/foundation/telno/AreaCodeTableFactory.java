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

package cherry.foundation.telno;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;

import cherry.goods.telno.SoumuExcelParser;

public class AreaCodeTableFactory implements FactoryBean<Trie<String, Integer>> {

	private SoumuExcelParser soumuExcelParser;

	private List<Resource> resources;

	public void setSoumuExcelParser(SoumuExcelParser soumuExcelParser) {
		this.soumuExcelParser = soumuExcelParser;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public Trie<String, Integer> getObject() throws Exception {
		Trie<String, Integer> trie = new PatriciaTrie<>();
		for (Resource r : resources) {
			try (InputStream in = r.getInputStream()) {
				Map<String, Pair<String, String>> map = soumuExcelParser.parse(in);
				for (Map.Entry<String, Pair<String, String>> entry : map.entrySet()) {
					trie.put(entry.getKey(), entry.getValue().getLeft().length());
				}
			}
		}
		return trie;
	}

	@Override
	public Class<?> getObjectType() {
		return Trie.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
