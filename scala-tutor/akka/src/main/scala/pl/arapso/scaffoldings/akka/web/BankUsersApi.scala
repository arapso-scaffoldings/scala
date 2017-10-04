package pl.arapso.scaffoldings.akka.web

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.ActorMaterializer
import spray.json._

import scala.util.Random

final case class User(firstName: String, lastName: String)

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val userFormat = jsonFormat2(User)
}

class UserService extends Directives with JsonSupport {

  val FirstNames = List("Michael", "James", "John", "Robert", "David", "William", "Mary", "Christopher", "Joseph", "Richard")
  val LastNames = List("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Rodriguez", "Miller", "Martinez", "Davis")

  private def randomElement(list: List[String]): String = {
    list(Random.nextInt(list.length))
  }

  private def getUser: User = {
    User(
      randomElement(FirstNames),
      randomElement(LastNames)
    )
  }

  private def getUsers(i : Int): List[User] = {
    (0 until i).map(_ => getUser).toList
  }

  val route: Route = path("users") {
    complete(getUsers(5))
  }
}

object BankUsersApi extends App {

  implicit val system = ActorSystem()
  implicit val materlializer = ActorMaterializer()

  val userService = new UserService()

  Http().bindAndHandle(userService.route, "localhost", 9191)

}
