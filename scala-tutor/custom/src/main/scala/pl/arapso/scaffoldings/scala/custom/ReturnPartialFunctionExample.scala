package pl.arapso.scaffoldings.scala.custom

object ReturnPartialFunctionExample extends App {

  sealed trait Fruit {
    def name: String
  }

  case class Apple(name: String) extends Fruit

  case class Banana(name: String) extends Fruit

  case class Orange(name: String) extends Fruit

  case class Blueberry(name: String) extends Fruit

  case class Strawberry(name: String) extends Fruit

  val fruits = Seq("apple", "banana", "orange", "blueberry", "strawberry")

  def some(name: String): PartialFunction[String, Fruit] = {
    case fruit@"apple" => Apple(name + fruit)
    case fruit@"banana" => Banana(fruit)
  }

  val result = fruits.map(some("Mój Frut"))
  println(result)

}
