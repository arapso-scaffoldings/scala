package pl.arapso.scaffoldings.scala.testing1

object Test1 {

  case class User(firstName: String, secondName: String)

  def main(args: Array[String]) {
    val temp = List(("Damian", "Ospara"), ("Ryszard", "Ospara"), ("Test", "TestValue"))

    temp.map(User.tupled).foreach(println)
  }

}