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
  val initHandler: Handler = new Handler {override def handle(context: Context) ={context.redirect("/redirected")}}
  val redirectedHandler: Handler = new Handler {override def handle(context: Context) ={context.html("redirected")}}
  val cockieHandler: Handler = new Handler {override def handle(context: Context) ={
    if (context.req.getCookies!=null){
      val cookie = context.req.getCookies
      cookie.foreach(println(_))
    }
    else context.redirect("/login")

""
  }}
  val loginHandler: Handler = new Handler {override def handle(context: Context) ={context.render("login.html") }}
  val postloginHandler: Handler = new Handler{override def handle(context: Context) ={
    val login = context.formParam("login")
    val passwd = context.formParam("passwd")
    context.cookie("login", login)
    context.cookie("passwd", passwd)
  }}


  def main(args: Array[String]): Unit = {
    var app: Javalin = Javalin.create.start(7777)
    app.get("/cookie", cockieHandler)
    app.get("/", rootHandler)
    app.post("/test", testHandler)
    app.post("/aj", testHandler)
    app.get("/load", loadIndex)
    app.post("/submit", postHandler)
    app.get("/ws", websocket)
    app.get("/login", loginHandler)
    app.get("/init", initHandler)
    app.post("/login", postloginHandler)
    app.get("/redirected", redirectedHandler)
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


