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

import static java.text.MessageFormat.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
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
import org.h2.jdbcx.JdbcDataSource;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.ParserProperties;

public class Main {

	private static final String SYSTEM_DATASOURCE_JNDI = "java:/datasources/SqlMan";

	private static final String DATASOURCE_JNDI_TEMPLATE = "java:/datasources/SqlMan.db{0}";
	private static final String DATASOURCE_DRIVER = "driver";
	private static final String DATASOURCE_URL = "url";
	private static final String DATASOURCE_USERNAME = "username";
	private static final String DATASOURCE_PASSWORD = "password";

	@Option(name = "-p", aliases = { "--port" }, metaVar = "PORT", usage = "HTTP port")
	private int httpPort = 8080;

	@Option(name = "-d", aliases = { "--url" }, metaVar = "URL", usage = "System DB URL")
	private String dbUrl = "jdbc:h2:file:./sqlman";

	@Option(name = "-U", aliases = { "--username" }, metaVar = "USERNAME", usage = "System DB username")
	private String dbUsername = "sa";

	@Option(name = "-P", aliases = { "--password" }, metaVar = "PASSWORD", usage = "System DB password")
	private String dbPassword = "";

	@Option(name = "-x", aliases = { "--context-path" }, metaVar = "PATH", hidden = true)
	private String contextPath = "/";

	@Option(name = "-w", aliases = { "--war" }, metaVar = "WAR", hidden = true)
	private File warFile = null;

	@Option(name = "-?", aliases = { "--help" }, help = true, hidden = true)
	private boolean help = false;

	@Argument(multiValued = true, metaVar = "CONFIG...", usage = "DB configuration files")
	private List<File> configFile;

	public Server prepareServer() throws Exception {

		Server server = new Server(httpPort);
		server.setStopAtShutdown(true);

		server.addBean(new Resource(SYSTEM_DATASOURCE_JNDI, createSystemDataSource(dbUrl, dbUsername, dbPassword)));
		if (configFile != null) {
			int count = 1;
			for (File file : configFile) {
				Properties props = new Properties();
				try (InputStream in = new FileInputStream(file)) {
					props.load(in);
				}
				String driver = props.getProperty(DATASOURCE_DRIVER);
				String url = props.getProperty(DATASOURCE_URL);
				String username = props.getProperty(DATASOURCE_USERNAME);
				String password = props.getProperty(DATASOURCE_PASSWORD);
				String jndiName = format(DATASOURCE_JNDI_TEMPLATE, count);
				server.addBean(new Resource(jndiName, createDataSource(driver, url, username, password)));
				count += 1;
			}
		}

		server.setHandler(createWebAppContext(contextPath, warFile));

		return server;
	}

	private DataSource createSystemDataSource(String url, String user, String password) {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setUrl(url);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		return dataSource;
	}

	private DataSource createDataSource(String driver, String url, String user, String password) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
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
			if (help) {
				System.err.println(format("java {0} [OPTIONS] [CONFIG...]", Main.class.getName()));
				parser.printUsage(System.err);
				return;
			}
		} catch (CmdLineException ex) {
			System.err.println(format("java {0} [OPTIONS] [CONFIG...]", Main.class.getName()));
			parser.printUsage(System.err);
			return;
		}

		startServer();
	}

	public static void main(String[] args) throws Exception {
		Main m = new Main();
		m.doMain(args);
	}

}
