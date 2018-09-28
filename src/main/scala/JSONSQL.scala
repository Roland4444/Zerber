import org.json4s.jackson.JsonMethods.parse
import zerber.co.uk.Executor

class JSONSQL {
  val emptyArr = parse("[]")

  import org.json4s.jackson.JsonMethods._

  val exec: Executor = new Executor("192.0.0.14", "ODAY", "roman", "rtm37dex", true)
  val js = new jSon

  def getJSonfromQuery(query: String): String = {
    var result = emptyArr
    println(js.JSON2String(result))
    val output = new StringBuffer
    val columnsName = exec.columnsName(query)
    val headerArray = js.ArrayList2JSON(columnsName)
    //println(s"HEADERARRAY=>${js.JSON2String(headerArray)}")
    result = js.appendArrArr_JValue(result, headerArray)
    val Resultset = exec.submit(query)
    var counter = 1
    while (Resultset.next()) {
      var newLine = emptyArr
      for (i <- 1 to columnsName.size())
        newLine = js.appendStrInJValue(newLine, Resultset.getString(i))
      val image = js.JSON2String(newLine)
      result = js.appendArrArr_JValue(result, newLine)
    }
    js.JSON2String(result)
  }
}