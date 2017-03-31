package pl.arapso.scaffoldings.scala.custom

/**
  * Created by damian0o on 06.01.17.
  */
object SimpleLoops {

  def main(args: Array[String]) {

    for (i <- 1 to 10) {
      println("Czesc Monika")
    }

    val n = 10
    var i = 1

    while(i < n) {
      i += 1
      println(s"$i Damian jest the best")
    }

    (1 to 10).
      flatMap(x => if(x % 2 == 0) Option(x) else None).
      map(x => s"$x Monika kocha damian").
      foreach(println)
  }

}
