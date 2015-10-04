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

package cherry.foundation.batch.tools;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cherry.foundation.batch.ExitStatus;
import cherry.foundation.batch.tools.Launcher;

public class Main {

	public static void main(String... args) {
		System.exit(doMain(args).getCode());
	}

	public static ExitStatus doMain(String... origArgs) {

		Options options = new Options();
		options.addOption("j", "jobid", true, "Job Id");
		options.addOption("h", "help", false, "Help");

		CommandLine cmdline = parse(options, origArgs);
		if (cmdline == null) {
			printHelp(options);
			return ExitStatus.FATAL;
		}
		if (cmdline.hasOption("h")) {
			printHelp(options);
			return ExitStatus.NORMAL;
		}

		String[] args = cmdline.getArgs();
		if (args.length <= 0) {
			return ExitStatus.FATAL;
		}

		String batchId = args[0];
		String[] newArgs = new String[args.length - 1];
		System.arraycopy(args, 1, newArgs, 0, args.length - 1);

		if (cmdline.hasOption("j")) {
			System.setProperty("jobId", cmdline.getOptionValue("j"));
		} else {
			System.setProperty("jobId", batchId);
		}

		Launcher launcher = new Launcher(batchId);
		ExitStatus status = launcher.launch(newArgs);
		return status;
	}

	private static CommandLine parse(Options options, String... args) {
		try {
			CommandLineParser parser = new GnuParser();
			return parser.parse(options, args);
		} catch (ParseException ex) {
			return null;
		}
	}

	private static void printHelp(Options options) {
		HelpFormatter help = new HelpFormatter();
		help.printHelp(Main.class.getName() + " [OPTIONS] batchId [args...]", options);
	}

}
