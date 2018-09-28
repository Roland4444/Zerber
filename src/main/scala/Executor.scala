package zerber.co.uk;
import java.sql._
import java.sql.DriverManager.getConnection
class Executor(ip: String, db: String, login: String, passwd: String, Usejtds: Boolean) {
  var cnnct = "jdbc:sqlserver://" + ip + "\\MSSQLSERVER:1433;database=" + db + ";user=" + login + ";password=" + passwd
  if (Usejtds)
    cnnct = "jdbc:jtds:sqlserver://" + ip + ":1433/" + db + ";instance=MSSQLSERVER;user=" + login + ";password=" + passwd
  val conn = getConnection(cnnct)

  def submit(query: String): ResultSet = {
    val stmt = conn.createStatement
    val res = stmt.executeQuery(query)
    res
  }
}