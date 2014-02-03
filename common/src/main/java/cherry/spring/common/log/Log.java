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

package cherry.spring.common.log;

import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;

public class Log {

	private static Properties messageDef;

	private final Logger logger;

	public static void setMessageDef(Properties msgDef) {
		messageDef = msgDef;
	}

	Log(Logger logger) {
		this.logger = logger;
	}

	public void debug(String msg, Object... args) {
		logger.debug(createMessage(msg, args));
	}

	public void debug(Throwable ex, String msg, Object... args) {
		logger.debug(createMessage(msg, args), ex);
	}

	public void log(ILogId id, Object... args) {
		switch (id.getLevel()) {
		case INFO:
			if (logger.isInfoEnabled()) {
				logger.info(createMessage(id, args));
			}
			break;
		case WARN:
			if (logger.isWarnEnabled()) {
				logger.warn(createMessage(id, args));
			}
			break;
		case ERROR:
			if (logger.isErrorEnabled()) {
				logger.error(createMessage(id, args));
			}
			break;
		default:
			if (logger.isErrorEnabled()) {
				logger.error(createMessage(id, args));
			}
			break;
		}
	}

	public void log(Throwable ex, ILogId id, Object... args) {
		switch (id.getLevel()) {
		case INFO:
			if (logger.isInfoEnabled()) {
				logger.info(createMessage(id, args), ex);
			}
			break;
		case WARN:
			if (logger.isWarnEnabled()) {
				logger.warn(createMessage(id, args), ex);
			}
			break;
		case ERROR:
			if (logger.isErrorEnabled()) {
				logger.error(createMessage(id, args), ex);
			}
			break;
		default:
			if (logger.isErrorEnabled()) {
				logger.error(createMessage(id, args), ex);
			}
			break;
		}
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	private String createMessage(String msg, Object... args) {
		return MessageFormat.format(msg, args);
	}

	private String createMessage(ILogId id, Object... args) {
		String msg = messageDef.getProperty(id.getId());
		return MessageFormat.format(msg, args);
	}

}
