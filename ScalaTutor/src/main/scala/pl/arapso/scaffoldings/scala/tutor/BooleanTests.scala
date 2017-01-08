package pl.arapso.scaffoldings.scala.tutor

/**
  * Created by damian0o on 13.12.16.
  */
object BooleanTests {

  def main(args: Array[String]) {
    val temp: Boolean = false
    val result = temp & true
    println(result)

    val temp2: Boolean = true
    val result2 = temp2 & true
    println(result2)


    val s = Seq(true, false, true, true)

    val allOdds = s.forall(_ == true)
    println(s"All odds $allOdds")

  }

}
