package pl.arapso.scaffoldings.scala.custom.fpis


sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tails: List[A]) extends List[A]

object List {

  def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B = l match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  @annotation.tailrec
  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
  }

  def sum(ints: List[Int]): Int =
    foldRight(ints, 0)(_ + _)

  def sumLeft(ints: List[Int]): Int =
    foldLeft(ints, 0)(_ + _)

  def product(ds: List[Double]): Double =
    foldRight(ds, 1.0)(_ * _)

  def length[A](as: List[A]): Int =
    foldRight(as, 0)((x, y) => 1 + y)

  def apply[A](as: A*): List[A] = {
    if(as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }

  def tail[A](ds: List[A]): List[A] = ds match {
    case Nil => Nil
    case Cons(_, tail) => tail
  }

  def setHead[A](ds: List[A], newHead: A): List[A] = ds match {
    case Nil => Nil
    case Cons(_, tail) => Cons(newHead, tail)
  }

  def drop[A](ds: List[A], n: Int): List[A] =
    if(n == 0) ds
    else ds match {
      case Nil => Nil
      case Cons(head, tail) => drop(tail, n - 1)
  }

  def dropWhile[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Nil => Nil
    case Cons(head, tail) => if(f(head)) dropWhile(tail)(f) else l
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  def init[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(_, Nil) => Nil
    case Cons(head, tail) => Cons(head, init(tail))
  }

}


object DataStructures extends App {

  val list = Cons("Dam", Cons("ia", Cons("n", Nil)))



}
