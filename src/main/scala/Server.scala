import java.util.function.Consumer

import io.javalin.websocket.{ConnectHandler, WsHandler}
import io.javalin.{Context, Handler, Javalin}
object Server{
  val JSONSQL: JSONSQL=new JSONSQL
  val rootHandler: Handler = new Handler {override def handle(context: Context) ={context.render("fake.html")      }}
  val loadIndex: Handler = new Handler {override def handle(context: Context) ={context.render("index.html")      }}

  val postHandler: Handler = new Handler {override def handle(context: Context) ={
    val query = context.formParam("query")
    val output = new StringBuffer
    println(query)
    output.append(JSONSQL.getJSonfromQuery(query))
    print(output.toString)
    context.html(output.toString)}
  }
  val testHandler: Handler = new Handler {override def handle(context: Context) ={
    print( context.formParam("name"))

    context.html("yey")   }}





  def main(args: Array[String]): Unit = {
    var app: Javalin = Javalin.create.start(7777)
    app.get("/", rootHandler)
    app.post("/test", testHandler)
    app.post("/aj", testHandler)
    app.get("/load", loadIndex)
    app.post("/submit", postHandler)


  }



}


