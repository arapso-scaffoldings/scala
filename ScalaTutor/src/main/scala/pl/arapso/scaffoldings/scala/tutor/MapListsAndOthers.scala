package pl.arapso.scaffoldings.scala.tutor

/**
  * Created by damian0o on 09.12.16.
  */
object MapListsAndOthers {

  case class DbUser(name: String, login: String, pass: String, tel: String)

  case class ClientUser(name: String, login: String)

  def dbToClient(dbUser: DbUser): ClientUser = ClientUser(dbUser.name, dbUser.login)

  def main(args: Array[String]) {

    val user1 = DbUser("user1", "user1Login", "pass1", "123123")
    val user2 = DbUser("user2", "user2Login", "pass2", "223123")
    val user3 = DbUser("user3", "user3Login", "pass3", "323123")
    val user4 = DbUser("user4", "user4Login", "pass4", "423123")
    val user5 = DbUser("user5", "user5Login", "pass5", "523123")

    val serverSideUsers = List(user1, user2, user3, user4, user5)

    val userIdsRequest = List("user1", "user3", "user5", "user6")

    val requestedUsers = userIdsRequest.map(userId => serverSideUsers
      .find(u => u.name == userId))
      .map(temp => temp.map(dbToClient).getOrElse(ClientUser("UnknownUser", "UnknownUser")))
    for(user <- requestedUsers) {
      println(user)
    }
  }

}
