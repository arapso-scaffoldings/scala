package pl.arapso.scaffoldings.scala.custom.calculator

import cats.effect.IO


object FunctionalCalculator {

  trait Operation {
    def execute(a: Int, b: Int): Int
  }

  object Add extends Operation {
    def execute(a: Int, b: Int): Int = a + b
  }

  object Subtract extends Operation {
    def execute(a: Int, b: Int): Int = a - b
  }

  object Multiplication extends Operation {
    def execute(a: Int, b: Int): Int = a * b
  }

  object Division extends Operation {
    def execute(a: Int, b: Int): Int = a / b
  }

  object Exit extends Operation {
    def execute(a: Int, b: Int): Int = ???
  }

  object NotKnowOperation extends Operation {
    def execute(a: Int, b: Int): Int = ???
  }

  val MainMenu: String =
    """
      |=============Functional Calculator 0.1=============
      |
      | 1) Add
      | 2) Subtract
      | 3) Multiplication
      | 4) Division
      | *) Exit
      |
    """.stripMargin

  def parseInput(s: String): Option[Operation] = s match {
    case "1" => Some(Add)
    case "2" => Some(Subtract)
    case "3" => Some(Multiplication)
    case "4" => Some(Division)
    case "*" => None
  }

  def readOption(): IO[Option[Operation]] = IO[Option[Operation]] {
    println(MainMenu)
    print("Enter: ")
    parseInput(scala.io.StdIn.readLine())
  }


  def main(args: Array[String]): Unit = {
    val program: IO[Option[Int]] = for {
      opt <- readOption()
      a <- IO[Int](Integer.parseInt(scala.io.StdIn.readLine()))
      b <- IO[Int](Integer.parseInt(scala.io.StdIn.readLine()))
    } yield opt.map(_.execute(a, b))

    val safe: IO[Unit] = program.runAsync(e => e match {
      case Left(e) => IO(println(s"ERROR $e"))
      case Right(r) => IO(println(r.map(i => s"Result $i").getOrElse("No result")))
    })

    safe.unsafeRunSync()
  }



}
