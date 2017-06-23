package pl.arapso.scaffoldings.scala.custom.collections


object SeqExample {

  def main(args: Array[String]): Unit = {

    sequenceExample

  }

  def sequenceExample = {
    val listWithInts: Seq[Int] = Seq(1, 2, 3, 4, 5, 6, 7)
    println(listWithInts)

    val prependZero = 0 +: listWithInts
    println(prependZero)

    val appendInts = prependZero :+ 8 :+ 9
    println(appendInts)
  }

}
