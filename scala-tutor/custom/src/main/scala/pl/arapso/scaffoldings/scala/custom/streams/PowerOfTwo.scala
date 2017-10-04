package pl.arapso.scaffoldings.scala.custom.streams

object PowerOfTwo extends App {
  def isPowerOfTwo(i: Int): Boolean = {
    if (i % 2 != 0) false
    else if (i < 2) false
    else if (i == 2) true
    else isPowerOfTwo(i / 2)
  }

  def isOdd(i: Int): Boolean = {
    i % 2 == 0
  }


  println(s"Int max ${Int.MaxValue}")
  Stream.from(1).
    filter(isOdd).
    filter(isPowerOfTwo).
    foreach(println)

  Thread.sleep(10000)

}
