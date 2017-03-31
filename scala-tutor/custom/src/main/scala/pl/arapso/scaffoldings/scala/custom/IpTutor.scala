package pl.arapso.scaffoldings.scala.custom

import java.lang.Integer
import java.net.InetAddress

/**
  * Created by damian0o on 13.12.16.
  */
object IpTutor {

  def main(args: Array[String]) {
    var temp: Int = 1;
    println(temp)

    temp = 0x7FFFFFFF
    println(Integer.toBinaryString(temp))

    print(Int.MaxValue)


  }
}
