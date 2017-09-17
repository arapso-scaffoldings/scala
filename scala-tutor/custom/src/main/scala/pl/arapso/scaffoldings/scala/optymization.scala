package pl.arapso.scaffoldings.scala

object optymization extends App {

  def DoNotEnterDeadCode = {
    val result = for {
      i <- 1 to 10000000
    } yield i
    result.sum
    1
  }

  for (_ <- 1 to 30) {
    val start = System.currentTimeMillis()
    val check = for (i <- 1 to 10)
      yield DoNotEnterDeadCode

    val stop = System.currentTimeMillis()
    println(s"Time ${stop - start}")

    check.sum
    Thread.sleep(1000)
  }
}
