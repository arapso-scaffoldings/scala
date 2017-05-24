package pl.arapso.scaffoldings.scala.types

import scala.reflect.ClassTag


object ArraysAndGenerics {

  class Parent(name: String) {
    override def toString: String = {
      name
    }
  }

  class Child(name: String) extends Parent(name) {
    def this(firstName: String, lastName: String) = {
      this(s"$firstName $lastName")
    }
  }

  object Child {
    def apply(test: Int): Child = new Child(s"Rys $test")
    def apply(name: String, name2: String): Child = new Child("GG", s"$name $name2")
  }

  def main(args: Array[String]) {

    def children: Array[Child] = Array(Child(12), Child("Damian", "Ospara"), new Child("Robert"))
    def parents: Array[Parent] = Array(new Parent("Maria"), new Parent("Waldemar"))

    def split[T:ClassTag](a: Array[T]): Array[T] = {
      Array[T](a(0))
    }

    println(children.mkString(", "))
    println(split(children).mkString(", "))

  }

}
