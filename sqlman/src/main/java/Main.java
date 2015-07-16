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
import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.jndi.Resource;
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
import org.h2.jdbcx.JdbcDataSource;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.ParserProperties;

public class Main {

	private static final String DB_JNDI = "java:/datasources/SqlMan";

	@Option(name = "-p", aliases = { "--port" }, metaVar = "PORT")
	private int serverPort = 8080;

	@Option(name = "-d", aliases = { "--db-url" }, metaVar = "URL")
	private String dbUrl = "jdbc:h2:file:./sqlman";

	@Option(name = "-U", aliases = { "--db-user" }, metaVar = "USER")
	private String dbUser = "sa";

	@Option(name = "-P", aliases = { "--db-password" }, metaVar = "PASSWORD")
	private String dbPassword = "";

	@Option(name = "-c", aliases = { "--config" }, metaVar = "CONFIG")
	private File jettyConfigXml = null;

	@Option(name = "-x", aliases = { "--context-path" }, metaVar = "PATH")
	private String contextPath = "/";

	@Option(name = "-w", aliases = { "--war" }, metaVar = "WAR")
	private File warFile = null;

	public Server prepareServer() throws Exception {

		Server server = new Server(serverPort);
		server.setStopAtShutdown(true);

		server.addBean(new Resource(DB_JNDI, createDataSource(dbUrl, dbUser, dbPassword)));
		if (jettyConfigXml != null) {
			XmlConfiguration xmlConfig = new XmlConfiguration(jettyConfigXml.toURI().toURL());
			xmlConfig.configure(server);
		}

		server.setHandler(createWebAppContext(contextPath, warFile));

		return server;
	}

	private DataSource createDataSource(String url, String user, String password) {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setUrl(url);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		return dataSource;
	}

	private WebAppContext createWebAppContext(String path, File war) throws MalformedURLException {
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath(path);
		webAppContext.setParentLoaderPriority(false);
		if (war == null) {
			webAppContext.setWar(Main.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm());
		} else {
			webAppContext.setWar(war.toURI().toURL().toExternalForm());
		}
		webAppContext.setConfigurations(new Configuration[] { new AnnotationConfiguration(), new WebInfConfiguration(),
				new WebXmlConfiguration(), new MetaInfConfiguration(), new FragmentConfiguration(),
				new EnvConfiguration(), new PlusConfiguration(), new JettyWebXmlConfiguration() });
		return webAppContext;
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
