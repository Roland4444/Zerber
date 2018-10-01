import java.util.function.Consumer

import io.javalin.websocket.{ConnectHandler, WsHandler}
import io.javalin.{Context, Handler, Javalin}
object Server{
  val JSONSQL: JSONSQL=new JSONSQL
  val rootHandler: Handler = new Handler {override def handle(context: Context) ={context.render("fake.html")      }}
  val loadIndex: Handler = new Handler {override def handle(context: Context) ={context.render("index.html")      }}

  val postHandler: Handler = new Handler {override def handle(context: Context) ={
    context.req.getParameter("query")
    val output = new StringBuffer
    output.append(JSONSQL.getJSonfromQuery(context.req.getParameter("query")))
    context.html(output.toString)}
  }
  val testHandler: Handler = new Handler {override def handle(context: Context) ={
    print( context.req.)

    println(context.mapFormParams().size())
    println(context.mapQueryParams().size())
    context.html("yey")   }}



  def main(args: Array[String]): Unit = {
    var app: Javalin = Javalin.create.start(7777)
    app.get("/", rootHandler)
    app.get("/test", testHandler)
    app.get("/aj", testHandler)
    app.get("/load", loadIndex)


  }



}


