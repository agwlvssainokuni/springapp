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

package cherry.example.common.io;

import java.io.File;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cherry.example.common.Config;
import cherry.foundation.bizdtm.BizDateTime;
import cherry.goods.util.JodaTimeUtil;

@Component
public class TempDirRepositoryImpl implements TempDirRepository, InitializingBean {

	@Autowired
	private Config config;

	@Autowired
	private BizDateTime bizDateTime;

	private File tempDirTop;

	@Override
	public void afterPropertiesSet() throws IllegalStateException {
		tempDirTop = new File(config.getBasedir(), config.getTempdirTopName());
		if (!tempDirTop.exists()) {
			if (!tempDirTop.mkdirs()) {
				throw new IllegalStateException("Failed to mkdirs " + tempDirTop.getPath());
			}
		}
	}

	@Override
	public String createTempDir() {
		Calendar cal = JodaTimeUtil.getCalendar(bizDateTime.now());
		UUID uuid = UUID.randomUUID();
		String name = MessageFormat.format(config.getTempdirPattern(), cal.getTime(), uuid.toString());
		File dir = new File(tempDirTop, name);
		if (dir.exists()) {
			throw new IllegalStateException("TempDir already exists " + dir.getPath());
		}
		if (!dir.mkdir()) {
			throw new IllegalStateException("Failed to mkdir " + dir.getPath());
		}
		return name;
	}

	@Override
	public File getTempDir(String name) throws IllegalArgumentException {
		File dir = getDir(name);
		if (!dir.exists()) {
			throw new IllegalArgumentException("Directory does not exists " + dir.getPath());
		}
		return dir;
	}

	@Override
	public void deleteTempDir(String name) throws IllegalArgumentException, IllegalStateException {
		File dir = getDir(name);
		if (!dir.exists()) {
			return;
		}
		deleteDir(dir);
	}

	private File getDir(String name) throws IllegalArgumentException {
		File dir = new File(tempDirTop, name);
		if (!dir.getName().equals(name)) {
			throw new IllegalArgumentException("Name is not valid " + name);
		}
		return dir;
	}

	private void deleteDir(File d) throws IllegalStateException {
		for (File f : d.listFiles()) {
			if (f.isDirectory()) {
				deleteDir(f);
			} else {
				if (!f.delete()) {
					throw new IllegalStateException("Failed to delete " + f.getPath());
				}
			}
		}
		if (!d.delete()) {
			throw new IllegalStateException("Failed to delete " + d.getPath());
		}
	}

}
