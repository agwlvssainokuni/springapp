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

import java.io.File;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.ParserProperties;

public class Main {

	@Option(name = "-p", aliases = { "--port" }, metaVar = "PORT")
	private int serverPort = 8080;

	@Option(name = "-c", aliases = { "--config" }, metaVar = "CONFIG")
	private File jettyConfigXml;

	@Option(name = "-x", aliases = { "--context-path" }, metaVar = "PATH")
	private String contextPath = "/";

	@Option(name = "-w", aliases = { "--war" }, metaVar = "WAR")
	private File warFile;

	public Server prepareServer() throws Exception {

		Server server = new Server(serverPort);
		server.setStopAtShutdown(true);

		XmlConfiguration xmlConfig = new XmlConfiguration(jettyConfigXml.toURI().toURL());
		xmlConfig.configure(server);

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath(contextPath);
		webAppContext.setParentLoaderPriority(false);
		if (warFile == null) {
			webAppContext.setWar(Main.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm());
		} else {
			webAppContext.setWar(warFile.toURI().toURL().toExternalForm());
		}
		webAppContext.setConfigurations(new Configuration[] { new AnnotationConfiguration(), new WebInfConfiguration(),
				new WebXmlConfiguration(), new MetaInfConfiguration(), new FragmentConfiguration(),
				new EnvConfiguration(), new PlusConfiguration(), new JettyWebXmlConfiguration() });
		server.setHandler(webAppContext);

		return server;
	}

	public void startServer() {
		try {
			Server server = prepareServer();
			server.start();
			server.join();
		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}

	public void doMain(String[] args) {

		ParserProperties props = ParserProperties.defaults().withUsageWidth(80);
		CmdLineParser parser = new CmdLineParser(this, props);
		try {
			parser.parseArgument(args);
		} catch (CmdLineException ex) {
			parser.printUsage(System.err);
		}

		startServer();
	}

	public static void main(String[] args) throws Exception {
		Main m = new Main();
		m.doMain(args);
	}

}
