package pl.arapso.scaffoldings.scala.scalalang

object ClassesExample extends App {

  val point = new Point(2, 3)
  println(point)
  point.move(3, 5)
  println(point)

}

class Point(var x: Int, var y: Int) {
  def move(dx: Int, dy: Int): Unit = {
    x = x + dx
    y = y + dy
  }
  override def toString: String =
    "(" + x + ", " + y + ")"
}
