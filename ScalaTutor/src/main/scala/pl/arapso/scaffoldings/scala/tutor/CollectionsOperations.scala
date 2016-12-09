package pl.arapso.scaffoldings.scala.tutor

/**
  * Created by damian on 08.12.16.
  */
object CollectionsOperations {

  def main(args: Array[String]) {
    val peoples = Seq("damian", "arek", "szymon", "basia", "radek", "czesaław")

    val witha = peoples.partition(x => x.contains("a"))
    println(witha._1.size)
    println(witha._2.size)
  }

}
