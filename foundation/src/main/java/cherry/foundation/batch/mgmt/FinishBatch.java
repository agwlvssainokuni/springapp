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

package cherry.foundation.batch.mgmt;

import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.batch.ExitStatus;
import cherry.foundation.batch.IBatch;
import cherry.foundation.bizdtm.BizDateTime;

public class FinishBatch implements IBatch {

	private BizDateTime bizDateTime;

	private BatchStatusStore batchStatusStore;

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	public void setBatchStatusStore(BatchStatusStore batchStatusStore) {
		this.batchStatusStore = batchStatusStore;
	}

	@Transactional
	@Override
	public ExitStatus execute(String... args) {

		if (args.length < 2) {
			return ExitStatus.ERROR;
		}

		String batchId = args[0];
		ExitStatus status = ExitStatus.valueOf(args[1]);

		int exitCode;
		if (args.length < 3) {
			exitCode = status.getCode();
		} else {
			exitCode = Integer.parseInt(args[2]);
		}

		if (!batchStatusStore.isBatchRunning(batchId)) {
			return ExitStatus.WARN;
		}

		batchStatusStore.updateToFinished(batchId, bizDateTime.now(), status, exitCode);

		return ExitStatus.NORMAL;
	}

}
