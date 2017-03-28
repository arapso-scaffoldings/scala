package pl.arapso.scaffoldings.scala.custom

/**
  * Created by damian on 08.12.16.
  */
object IntegersTests {

  def main(args: Array[String]) {
    {
      val temp = createOperations addTwoIntegers (1, 2)
      println(temp)
    }

    {
      val temp = createOperations addTuple((1, 2))
      println(temp)
    }

  }

  implicit def createOperations() : IntegerOperations = new IntegerOperations()

}
