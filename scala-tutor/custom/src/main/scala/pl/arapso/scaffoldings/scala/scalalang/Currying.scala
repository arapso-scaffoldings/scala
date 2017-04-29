package pl.arapso.scaffoldings.scala.scalalang

object Currying {

  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    if(a > b) 0
    else f(a) + sum(f)(a + 1, b)
  }

  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if(a > b) 1
    else f(a) * product(f)(a + 1, b)
  }

  def fact(b: Int): Int = {
    product(x => x)(1, b)
  }


  def generalized(i: Int, f1: (Int, Int) => Int, f2: Int => Int)(a: Int, b: Int): Int = {
    if(a > b) i
    else f1(f2(a), generalized(i, f1, f2)(a + 1, b))
  }


  def main(args: Array[String]) {
    println(fact(5))
  }

}