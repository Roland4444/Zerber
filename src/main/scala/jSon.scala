import java.io.PrintWriter
import java.util
import java.util.Map

import org.json.JSONObject
import org.json4s._
import org.json4s.jackson.JsonMethods._
import zerber.co.uk.Executor
class jSon{
  def appendArrayToArray_Str(input: String, appended: String)={
    compact(render(parse(input) ++ parse(appended)))
  }

  def appendStrInJValue(input: JValue, append: String):JValue={
    input++(new JString(append))
  }

  def appendInArray(input: JValue, append: JValue):JValue={
    input++append
  }

  def appendArrArr_JValue(input: JValue, append: JValue)={
    val inject = JSON2String(append)
    val inputStr = JSON2String(input)
    var result = inputStr.substring(0,inputStr.length-1)+","+inject+"]"
    if (inputStr == "[]")
     result = inputStr.substring(0,inputStr.length-1)+inject+"]"
    parse(result)
  }

  def ArrayList2JSON(input: util.ArrayList[String]):JValue = {
    var result=parse("[]")
    for (i <- 0 to input.size()-1)
     result=appendStrInJValue(result, input.get(i))
    result
  }

  def JSON2String(jv: JValue):String={
    compact(render(jv))
  }

  def Map2JSON(input: java.util.Map[String, String]): JSONObject = {
    val it = input.entrySet.iterator
    val res = new JSONObject
    while ( {
      it.hasNext
    }) {
      val pair = it.next.asInstanceOf[util.Map.Entry[_, _]]
      val key = pair.getKey.asInstanceOf[String]
      val value = pair.getValue.asInstanceOf[String]
      res.append(key, value)
    }
    res
  }
}

class jsonTestDefs(){
  val primitive4test = parse("[\"2\", \"3\"]")
  val added_test = parse("[\"2\"]")
  val result_test = parse("[\"2\", \"3\",[\"2\"]]")
}

