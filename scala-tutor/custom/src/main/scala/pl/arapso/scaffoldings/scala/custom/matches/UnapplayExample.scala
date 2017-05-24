package pl.arapso.scaffoldings.scala.custom.matches


object UnapplayExample {

  case class SomeStuff(name: String)

  def main(args: Array[String]) {

    val test = new SomeStuff("kupon")

    val result = test match {
      case SomeStuff("kuponik") => "OK"
      case _ => "NOT OK"
    }
    println(result)

  }
}
