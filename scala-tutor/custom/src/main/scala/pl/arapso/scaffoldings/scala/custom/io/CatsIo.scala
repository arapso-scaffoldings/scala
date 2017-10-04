package pl.arapso.scaffoldings.scala.custom.io

import cats.effect.IO

object CatsIo extends App {

  def showMsg(msg: String): IO[Unit] = IO {
    println(msg)
  }

  def f(a: IO[Unit], b: IO[Unit]) = for {
    ta <- a
    tb <- b
  } yield ()

  val r1 = f(showMsg("a"), showMsg("a"))

  r1.unsafeRunSync()

  val x: IO[Unit] = showMsg("a")

  val r2 = f(x, x)

  r2.unsafeRunSync()

}
