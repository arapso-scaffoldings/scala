package pl.arapso.scaffoldings.akka

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

/**
  * Simple example how tu create rest api proxy wuth akka akka-http.
  */
object SwApiProxy extends App {
  implicit val system = ActorSystem()
  implicit val materlialize = ActorMaterializer()
  import system.dispatcher


  val sw_get = path("/hello") & get & parameters("param1", "param2")

  val route = sw_get { (param1: String, param2: String) =>
    complete(s"OK ${param1} ${param2}")
  }

  Http().bindAndHandle(route, "localhost", 8080)


}
