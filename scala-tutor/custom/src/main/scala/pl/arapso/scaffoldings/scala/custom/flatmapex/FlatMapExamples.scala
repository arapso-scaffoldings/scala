package pl.arapso.scaffoldings.scala.custom.flatmapex

object FlatMapExamples {

  def get(a: String): Option[String] = {
    println(a)
    if (a == "none")
      None
    else
      Some(a)
  }

  def main(args: Array[String]): Unit = {


    val result = for {
      a <- get("a")
      b <- get("b")
      c <- get("none")
      d <- get("d")
    } yield (a, b)


    print(result)
  }

}
