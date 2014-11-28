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

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface AsyncFileProcessHandler {

	/**
	 * 非同期のファイル処理を実行登録する。
	 * 
	 * @param launcherId
	 *            非同期処理の実行者のID。
	 * @param file
	 *            処理対象のファイル。
	 * @param handlerName
	 *            非同期のファイル処理の処理を実装したBeanの名前。同Beanは{@link FileProcessHandler}
	 *            を実装しなければならない。
	 * @return 非同期実行状況の管理データのID。
	 * @throws IOException
	 *             一時ファイル作成で異常発生。
	 */
	long launchFileProcess(String launcherId, MultipartFile file,
			String handlerName) throws IOException;

	void handleMessage(Map<String, String> message);

}
