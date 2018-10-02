import java.util.function.Consumer

import io.javalin.websocket.{ConnectHandler, WsHandler}
import io.javalin.{Context, Handler, Javalin}
object Server{
  val JSONSQL: JSONSQL=new JSONSQL
  val websocket: Handler = new Handler {override def handle(context: Context) ={context.render("ws.html")      }}
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
    context.html(context.formParam("name"))   }}
  def main(args: Array[String]): Unit = {
    var app: Javalin = Javalin.create.start(7777)
    app.get("/", rootHandler)
    app.post("/test", testHandler)
    app.post("/aj", testHandler)
    app.get("/load", loadIndex)
    app.post("/submit", postHandler)
    app.get("/ws", websocket)
    app.ws("/ws",  ws => {
      ws.onConnect(session => System.out.println("Connected"));
      ws.onMessage((session, message) => {
        System.out.println("Received: " + message);
        session.getRemote().sendString(JSONSQL.getJSonfromQuery(message));
      });
      ws.onClose((session, statusCode, reason) => System.out.println("Closed"));
      ws.onError((session, throwable) => System.out.println("Errored"));
    });
  }
}


