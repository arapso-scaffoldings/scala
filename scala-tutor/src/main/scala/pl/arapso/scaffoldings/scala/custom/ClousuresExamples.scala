package pl.arapso.scaffoldings.scala.custom

/**
  * Created by damian0o on 09.12.16.
  */
object ClousuresExamples {

  def temp(a: Int): String = {
    {
      if(a % 2 == 0){
        "Hello World"
      } else {
        "Default value"
      }
    }
  }


  def main(args: Array[String]): Unit = {

    println(temp(2))
    println(temp(1))

  }


}
