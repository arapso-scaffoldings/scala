package pl.arapso.scaffoldings.scala.futures

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object SimpleAsyncCalculator extends App {

  def printResult: PartialFunction[Int, Unit] = {
    case x: Int => println(s"Result $x")
  }

  def forComprehensionExample = {
    val result = for {
      a <- f_a
      b <- f_b
    } yield a + b
    result.onSuccess(printResult)
  }

  def flatMapExample = {
    val result = f_a.flatMap { a =>
      f_b.flatMap { b =>
        Future {
          a + b
        }
      }
    }
    result.onSuccess(printResult)
  }

  def foldExample = {
    val listOfFutureInts = List(f_a, f_b)
    val result: Future[Int] = Future.fold(listOfFutureInts)(0)(_ + _)
    result.onSuccess(printResult)
  }

  val f_a = Future {
    1
  }

  val f_b = Future {
    2
  }

  forComprehensionExample

  flatMapExample

  foldExample

  Thread.sleep(2000)

}
