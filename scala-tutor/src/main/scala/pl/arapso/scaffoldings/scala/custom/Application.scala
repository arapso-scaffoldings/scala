package pl.arapso.scaffoldings.scala.custom

/**
  * Created by damian on 08.12.16.
  */
object Application {

  case class User(firstName: String, lastName: String, age: Int)

  case class SameUser(firstName: String, lastName: String, age: Int)

  case class SimpleUser(firstName: String)

  def main(args: Array[String]) {
    val user1 = new User("damian", "Ospara", 12)

    // print property of case class
    println(s"Hello World ${user1.firstName}")

    // print whole object
    println(user1)

    // print properties in one string
    println(s"Hello ${user1.firstName} ${user1.lastName} ${user1.age}")

    // compare two instances of case class
    val user2 = new User("damian", "Ospara", 12)
    println(user1 == user2)

    val user3 = new User("arek", "lewy", 15)
    println(user1 == user3)

    // compare two instances of different case classes
    val simpleUser1 = SimpleUser("damian")
    println(user1 == simpleUser1)

    val sameUser1 = new SameUser("damian", "Ospara", 12)
    println(user1 == sameUser1)

  }

}
