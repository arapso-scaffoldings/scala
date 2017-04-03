import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.MediaType
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer


object WebServer extends App {
  implicit val system = ActorSystem()
  implicit val materlialize = ActorMaterializer()
  import system.dispatcher


  val hello_get = path("/hello") & get & parameters("param1", "param2")

  val route = hello_get { (param1: String, param2: String) =>
    complete(s"OK ${param1} ${param2}")
  }

  Http().bindAndHandle(route, "localhost", 8080)


  val t = 'sss
  val sd = 'ad

}