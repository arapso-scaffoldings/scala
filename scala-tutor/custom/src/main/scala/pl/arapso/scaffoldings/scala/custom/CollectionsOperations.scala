package pl.arapso.scaffoldings.scala.custom

/**
  * Created by damian on 08.12.16.
  */
object CollectionsOperations {

  def main(args: Array[String]) {
    val peoples = Seq("damian", "arek", "szymon", "basia", "radek", "czesaław")

    val witha = peoples.partition(x => x.contains("a"))
    println(witha._1.size)
    println(witha._2.size)

    // filter
    val numbers = 1 to 10
    println(numbers)

    val oddNumbers = numbers.filter(x => x == 2)

    for(i <- oddNumbers) {
      print(s"$i\t")
    }

    // mkstring
    val httpGetParameters = List("asd", "asd", "asda", "asd")
    println(httpGetParameters.mkString("c=", "&c=", ""))


  }

}
