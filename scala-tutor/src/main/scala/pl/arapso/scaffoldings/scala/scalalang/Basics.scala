package pl.arapso.scaffoldings.scala.scalalang

// http://docs.scala-lang.org/tutorials/
object Basics extends BasePrintln {

}

trait BasePrintln extends App {
    println(1)
    println(1 + 1)
    println("Hello World!")
    println("Hello, " + "World!")

    val x = 1 + 2
    println(x)

    val y: Int = 2 + 5
    println(y)

    println({
      val x = 1 + 4
      val y = x * 2
      y
    })

    val inc = (x: Int) => x + 1
    println(inc(1))

    val add = (x: Int, y: Int) => x + y
    println(add(2, 1))

    val getSome = () => 42
    println(getSome())

    def add2(x: Int, y: Int) = {
      x + y
    }
    println(add2(2, 5))
}