import io.javalin.{Context, Handler, Javalin}
object Server{
  val JSONSQL: JSONSQL=new JSONSQL
  val rootHandler: Handler = new Handler {override def handle(context: Context) ={context.render("fake.html")      }}
  val postHandler: Handler = new Handler {override def handle(context: Context) ={
    context.req.getParameter("query")
    val output = new StringBuffer
    output.append(JSONSQL.getJSonfromQuery(context.req.getParameter("query")))
    context.html(output.toString)}
  }
  val testHandler: Handler = new Handler {override def handle(context: Context) ={context.html("yey")     }}


  def main(args: Array[String]): Unit = {
    var app: Javalin = Javalin.create.start(7777)
    app.get("/", rootHandler)
    app.get("/test", testHandler)
    app.post("/", postHandler)
  }
}


