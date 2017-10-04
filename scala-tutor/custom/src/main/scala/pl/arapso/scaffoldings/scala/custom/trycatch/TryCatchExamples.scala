package pl.arapso.scaffoldings.scala.custom.trycatch

import scala.util.{Failure, Success, Try}

/**
  * Simple Try example.
  * Read user input. User should filled two integer numbers.
  * If user specified numbers was valid result will contain division result a/b.
  * If some error occur both parsing or computation program will display properly written message.
  */

case class Input(input: String)

case object Input {
  def readInput(msg: String): Input = {
    print(s"$msg:")
    Input(scala.io.StdIn.readLine())
  }
}

case class InputException(fields: Array[String]) extends Exception


object TryCatchExamples {

  def parse(i: String): Try[Int] = {
    Try {
      Integer.parseInt(i)
    }
  }

  def example1(): Unit = {
    val a = parse(scala.io.StdIn.readLine())
    val b = parse(scala.io.StdIn.readLine())

    val result = a.flatMap(x => b.flatMap(y => {
      Try {
        x / y
      }
    }))
    result match {
      case Success(r) => println(s"Result is $r")
      case Failure(e) => println(s"Can not divide two entered numbers exception: ${e}")
    }
  }

  def matchExample(input: (Input, Input)): Try[Int] = {
    val a = parse(input._1.input)
    val b = parse(input._2.input)
    val result = (a, b) match {
      case (Success(a), Success(b)) => Try(a / b)
      case (Failure(e1), Failure(e2)) => Failure(new InputException(Array("A was invalid", "B was invalid")))
      case (Failure(_), Success(_)) => Failure(new InputException(Array("A was invalid")))
      case (Success(_), Failure(_)) => Failure(new InputException(Array("B was invalid")))
      case _ => Failure(new UnknownError("Not handled exception occured"))
    }
    result
  }


  def main(args: Array[String]): Unit = {
    val aStream = (1 to 4).toStream.map(_ => Input.readInput("Give me A"))
    val bStream = (1 to 4).toStream.map(_ => Input.readInput("Give me B"))

    val zippedStream = aStream.zip(bStream)

    val results = zippedStream.map(matchExample).toList

    println(results)

  }

}
