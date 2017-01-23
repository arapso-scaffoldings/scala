package pl.arapso.scaffoldings.scala.tutor.fpis


sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tails: List[A]) extends List[A]

object List {

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = {
    if(as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }

  def tail[A](ds: List[A]): List[A] = ds match {
    case Nil => Nil
    case Cons(head, tail) => tail
  }

  def setHead[A](ds: List[A], newHead: A): List[A] = ds match {
    case Nil => Nil
    case Cons(head, tail) => Cons(newHead, tail)
  }

  def drop[A](ds: List[A], n: Int): List[A] = ds match {
    case Nil => Nil
    case Cons(head, tail) => drop(tail, n - 1)
  }
}


object DataStructures extends App {

  val list = Cons("Dam", Cons("ia", Cons("n", Nil)))



}
