package pl.arapso.scaffoldings.akka.streams

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.stream.ActorMaterializer
import spray.json.{DefaultJsonProtocol, _}
import akka.http.scaladsl.unmarshalling.Unmarshal

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

case class User(firstName: String, lastName: String)

trait ApplicationProtocol extends DefaultJsonProtocol {
  implicit val appFormat = jsonFormat2(User)
}

object ReadUsersFromExternalApi extends App with ApplicationProtocol {
  implicit val system = ActorSystem()
  implicit val materlializer = ActorMaterializer()

  def getUsers(size: Int): List[User] = {
    val response = Http().singleRequest(HttpRequest(uri = "http://localhost:9191/users"))

    val listOfUsers: Future[List[User]] = for {
      res <- response
      temp <- Unmarshal(res.entity).to[List[User]]
    } yield temp


    Await.result(listOfUsers, 3 seconds)
  }

  val users = getUsers(5)
  users.foreach(println)
  system.terminate()

}
