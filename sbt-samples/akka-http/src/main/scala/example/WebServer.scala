package example

import java.time.LocalDate

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.unmarshalling.Unmarshaller
import akka.stream.ActorMaterializer

import scala.concurrent.Future
import scala.io.StdIn

object WebServer {
  def main(args: Array[String]) {
    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    def route = {
      implicit val localDateUnmarshaller = Unmarshaller[String, LocalDate] { ex => str =>
        Future {
          LocalDate.parse(str)
        }
      }

      path("hello") {
        get {
          parameters('fromDate.as[LocalDate], 'toDate.as[LocalDate]) { (fromDate, toDate) =>
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`,
              <h1>{fromDate} - {toDate}</h1>.toString()))
          }
        }
      }
    }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
