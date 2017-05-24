package pl.arapso.scaffoldings.scala.funproginscala

abstract class MyBoolean {

  def ifThenElse[T](t: T, e: T): T

  def && (other: MyBoolean) = ifThenElse(MyTrue, other)

}

object MyTrue extends MyBoolean {
  override def ifThenElse[T](t: T, e: T): T = t
}

object MyFalse extends MyBoolean {
  override def ifThenElse[T](t: T, e: T): T = e
}

object Excercise {

  def main(args: Array[String]) {

  }

}

class Nat {

}

object Zero {

}

class Succ {

}
