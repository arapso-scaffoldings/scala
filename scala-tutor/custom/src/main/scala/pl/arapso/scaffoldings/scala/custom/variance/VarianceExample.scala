package pl.arapso.scaffoldings.scala.custom.variance

object VarianceExample extends App {

  class Tool(name: String)

  object Tool {
    def apply(name: String): Tool = new Tool(name)
  }

  case class Hammer(name: String, power: Int) extends Tool(name)

  def getPartialFunction: PartialFunction[String, Tool] = {
    case "dajmlotek" => Hammer("Super mlotek", 2)
    case "daj toola" => Tool("Slaby Tool")
    case _ => Tool("Dowolny tool")
  }

  val p: Tool = getPartialFunction("dajmlotek")
  println(p)

}
