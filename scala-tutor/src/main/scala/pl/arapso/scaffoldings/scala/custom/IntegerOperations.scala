package pl.arapso.scaffoldings.scala.custom

/**
  * Created by damian on 08.12.16.
  */
class IntegerOperations {

  def addTwoIntegers(a: Int, b: Int): Int = {
    a + b
  }

  def addTuple(input: (Int, Int)): Int = {
    input._1 + input._2
  }
}
