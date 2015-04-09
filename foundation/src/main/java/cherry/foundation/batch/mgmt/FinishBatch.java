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

import cherry.foundation.batch.ExitStatus;
import cherry.foundation.batch.IBatch;

public class FinishBatch implements IBatch {

	private BatchStatusService batchStatusService;

	public void setBatchStatusService(BatchStatusService batchStatusService) {
		this.batchStatusService = batchStatusService;
	}

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

		if (batchStatusService.updateToFinished(batchId, status, exitCode)) {
			return ExitStatus.NORMAL;
		} else {
			return ExitStatus.WARN;
		}
	}

}
