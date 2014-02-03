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

import static java.text.MessageFormat.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cherry.spring.batch.ExitStatus;
import cherry.spring.batch.IBatch;

public class Launcher {

	public static final String APPCTX = "classpath:config/applicationContext.xml";

	private Logger log = LoggerFactory.getLogger(getClass());

	private String batchId;

	public Launcher(String batchId) {
		this.batchId = batchId;
		MDC.put("batchId", batchId);
	}

	public ExitStatus launch(String... args) {
		try {

			log.info(format("BATCH {0} STARTING", batchId));
			for (String arg : args) {
				log.info(format("  {0}", arg));
			}

			ApplicationContext appCtx = new ClassPathXmlApplicationContext(
					APPCTX);
			IBatch batch = appCtx.getBean(batchId, IBatch.class);

			log.info(format("BATCH {0} STARTED", batchId));
			ExitStatus status = batch.execute(args);

			switch (status) {
			case NORMAL:
				log.info(format("BATCH {0} ENDED WITH {1}", batchId,
						status.name()));
				break;
			case WARN:
				log.warn(format("BATCH {0} ENDED WITH {1}", batchId,
						status.name()));
				break;
			case ERROR:
				log.error(format("BATCH {0} ENDED WITH {1}", batchId,
						status.name()));
				break;
			default:
				log.error(format("BATCH {0} ENDED WITH {1}", batchId,
						status.name()));
				break;
			}

			return status;

		} catch (Exception ex) {
			log.error(format("BATCH {0} ENDED WITH EXCEPTION", batchId), ex);
			return ExitStatus.FATAL;
		}
	}

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
