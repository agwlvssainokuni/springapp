@Grab("com.h2database:h2:1.4.178")
import org.h2.tools.Server
Server.main("-tcp", "-tcpPort", "9092")
