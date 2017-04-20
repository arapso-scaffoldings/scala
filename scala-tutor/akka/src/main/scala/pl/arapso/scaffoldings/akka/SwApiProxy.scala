package pl.arapso.scaffoldings.akka

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

/**
  * Simple example how tu create rest api proxy with akka akka-http.
  */
object SwApiProxy extends App {
  implicit val system = ActorSystem()
  implicit val materlializer = ActorMaterializer()

  import system.dispatcher

  val param1 = parameter('param1)
  val param2 = parameter('param2)

  val helloParameters = param1 & param2

  val sw_get = get & path("luke")

  val route: Route = sw_get {
    val response = Http().singleRequest(HttpRequest(uri = "https://swapi.co/people/1"))
    complete(response.map(_.entity.toString))
  }

  // TODO print on console basic binding events http bind end etc
  Http().bindAndHandle(route, "localhost", 8080)

}
