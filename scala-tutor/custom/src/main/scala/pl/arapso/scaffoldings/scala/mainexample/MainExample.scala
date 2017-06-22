package pl.arapso.scaffoldings.scala.mainexample

trait MainMethodTrait {

  def not_main(args: Array[String]): Unit = {
    println("Hello I am trait")
  }

}

trait Main2MethodTrait extends MainMethodTrait {

  override def not_main(args: Array[String]): Unit = {
    println("Hello 2 I am trait")
  }

}


trait Main3MethodTrait extends MainMethodTrait {

  override def not_main(args: Array[String]): Unit = {
    println("Hello 3 I am trait")
  }

}

object HelloTrait extends Main2MethodTrait with Main3MethodTrait {

  def main(args: Array[String]): Unit = {
    not_main(args)
  }

}
