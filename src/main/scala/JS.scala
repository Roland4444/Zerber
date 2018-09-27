import org.apache.commons.compress.utils.Lists
import org.json.JSONObject
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
object JS{
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
  }

}