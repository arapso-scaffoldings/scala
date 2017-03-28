package pl.arapso.scaffoldings.scala.scalalang


object StartExample extends App {

  val temp = new TraitsMixinsExample
  println(temp.add(1, 2))

}

class TraitsMixinsExample extends AddTrait1 with AddTrait2 {

  override def add(x: Int, y: Int): Int = {
    println("Traits mixins example")
    (x + y) * 3
    super[AddTrait1].add(x, y)
  }

}

trait AddTrait1 {
  def add(x: Int, y: Int): Int = {
    println("Add trait 1")
    x + y
  }
}

trait AddTrait2 {
  def add(x: Int, y: Int): Int = {
    println("Add trait 2")
    (x + y) * 2
  }
}
