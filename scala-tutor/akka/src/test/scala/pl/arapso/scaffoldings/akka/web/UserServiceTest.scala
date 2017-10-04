package pl.arapso.scaffoldings.akka.web

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{FlatSpec, Matchers}

class UserServiceTest extends FlatSpec with Matchers with ScalatestRouteTest with JsonSupport {

  behavior of "UserService"

  it should "return single user" in {
    val route = new UserService().route
    Get("/users") ~> route ~> check {
      responseAs[List[User]] should {
        have length 5
      }
    }

  }

}
