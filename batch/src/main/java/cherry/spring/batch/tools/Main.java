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

package cherry.spring.batch.tools;

import cherry.spring.batch.ExitStatus;

public class Main {

	public static void main(String... args) {

		if (args.length <= 0) {
			System.exit(ExitStatus.FATAL.getCode());
		}

		String batchId = args[0];
		String[] newArgs = new String[args.length - 1];
		System.arraycopy(args, 1, newArgs, 0, args.length - 1);

		Launcher launcher = new Launcher(batchId);
		ExitStatus status = launcher.launch(newArgs);
		System.exit(status.getCode());
	}

}
