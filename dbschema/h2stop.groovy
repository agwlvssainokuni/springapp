@Grab("com.h2database:h2:1.4.177")
import org.h2.tools.Server
Server.main("-tcpShutdown", "tcp://localhost:9092")
