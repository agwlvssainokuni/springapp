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

package cherry.spring.fwcore.async;

import org.joda.time.LocalDateTime;

import cherry.spring.fwcore.command.CommandResult;

public interface AsyncStatusStore {

	AsyncStatus getCurrentStatus(long asyncId);

	long createFileProcess(String launcherId, LocalDateTime dtm, String name,
			String originalFilename, String contentType, long size,
			String handlerName);

	long createCommand(String launcherId, LocalDateTime dtm, String... command);

	void updateToLaunched(long asyncId, LocalDateTime dtm);

	void updateToProcessing(long asyncId, LocalDateTime dtm);

	void finishFileProcess(long asyncId, LocalDateTime dtm, AsyncStatus status,
			FileProcessResult result);

	void finishCommand(long asyncId, LocalDateTime dtm, AsyncStatus status,
			CommandResult result);

	void finishWithException(long asyncId, LocalDateTime dtm, Throwable th);

}
