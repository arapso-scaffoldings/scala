package pl.arapso.scaffoldings.scala.scalalang.collections

object MapExamples {

  def main(args: Array[String]) {
    val tuple1 = "1" -> 2
    val tuple2 = "2" -> 3
    val tuple3 = "3" -> 4

    val map = Map(tuple1, tuple2, tuple3)

    map.foreach(x => {
      println(s"${x._1} -> ${x._2}")
    })
  }
}
