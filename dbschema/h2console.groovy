@Grab("com.h2database:h2:1.4.178")
import org.h2.tools.Shell
Shell.main("-url", "jdbc:h2:tcp://localhost/mem:appdb", "-user", "sa")
