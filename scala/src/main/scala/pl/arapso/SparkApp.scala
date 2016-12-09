package pl.arapso

import java.nio.file.{Files, Paths}

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object SparkApp {

  def sd: ((Boolean, Iterable[Int])) => Unit = {
    case (key, value) => {
      val writer = Files.newBufferedWriter(Paths.get("key"))
      writer.write(value mkString "/")
    }
  }

  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local", "SparkApp")
    sc.parallelize(1 to 20).map(x => (x%2==0, x)).groupByKey().foreach{sd}
  }
}
