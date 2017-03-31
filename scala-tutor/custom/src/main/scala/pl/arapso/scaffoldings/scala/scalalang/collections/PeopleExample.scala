package pl.arapso.scaffoldings.scala.scalalang.collections

import scala.util.Sorting


object PeopleExample {

  class Gender(symbol: String)
  case class Male() extends Gender("M")
  case class Female() extends Gender("F")

  case class Human[T >: Gender](name: String, age: Int, gender: T)

  def main(args: Array[String]) {

    var damian = Human("Damian", 27, Male)
    val rys = Human("Ryś", 5, Male)
    val monika = Human("Monika", 26, Female)
    val olis = Human("Oliś", 6, Male)

    val people = List(damian, rys, monika, olis)
    println(people)

    damian = Human("Damian JEst cool", 21, Male)
    println(people)

    val (kids, adults) = people.partition(_.age < 18)
    println(kids)
    println(adults)

    val peoplesSeq = Seq(damian, monika, olis, rys)
    val (male, female) = peoplesSeq.partition(_.gender == Male)
    println(male)
    println(female)

    object AgeOrdering extends Ordering[Human[Object]] {
      override def compare(x: Human[Object], y: Human[Object]): Int = x.age compare y.age
    }

    val array = people.toArray
    Sorting.quickSort(array)(AgeOrdering)
    array.foreach(print(_))
    println()
    println(people)

  }

}
