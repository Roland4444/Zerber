import java.io.PrintWriter
import java.util
import java.util.Map

import org.json.JSONObject
import org.json4s._
import org.json4s.jackson.JsonMethods._
import zerber.co.uk.Executor
class jSon{

  def main(args: Array[String]) {
    val JSONString = """
      {
         "name":"luca",
         "id": "1q2w3e4r5t",
         "age": 26,
         "url":"http://www.nosqlnocry.wordpress.com",
         "url":"https://nosqlnocry.wordpress.com",
         "loginTimeStamps": [1434904257,1400689856,1396629056],
         "messages": [
          {"id":1,"content":"Please like this post!"},
          {"id":2,"content":"Forza Roma!"}
         ],
         "profile": { "id":"my-nickname", "score":123, "avatar":"path.jpg" }
      }
      """
    val JSON = parse(JSONString)
    val JS1: JSONObject = new JSONObject()
    val JS2: JSONObject = new JSONObject()
    val value1 : JValue = new JString("12")
    val lists =List (value1, value1)
    val JArr: JArray = new JArray(lists);
    JS2.append("key2", JSONString)
    JS1.append("key", JS2)
    JS1.put("", JArr)
    println((JS1.toString))
    println((JArr))

    val doc = render(JArr)
    val compactJson = compact(doc)
    val prettyJson = pretty(doc)
    println(s"compact:\n$compactJson\n\npretty:\n$prettyJson")
    val pr = new PrintWriter("out")
    pr.write(compactJson)
    pr.close
  }


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

