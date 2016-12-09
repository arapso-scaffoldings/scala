package pl.arapso.scaffoldings.scala.tutor

/**
  * Created by damian on 08.12.16.
  */
object MapsExamples {

  def main(args: Array[String]) {
    var simpleMap = Map("a" -> "b", "c" -> "d")
    println(simpleMap)

    simpleMap += ("e" -> "f")
    println(simpleMap)

    simpleMap updated ("g", "h")
    println(simpleMap)

    simpleMap = simpleMap updated ("g", "h")
    println(simpleMap)

    //=======================================================
    println(simpleMap("e"))

  }

}
