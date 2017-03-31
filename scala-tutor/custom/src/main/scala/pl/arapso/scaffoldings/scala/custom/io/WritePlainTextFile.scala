package pl.arapso.scaffoldings.scala.custom.io

import java.io._

/**
  * Created by damian0o on 06.01.17.
  */
object WritePlainTextFile {

  def isDirExists()(implicit dir: File) = {
    println(s"Dir exists?: ${dir.exists}")
  }

  def main(args: Array[String]) {
    implicit val dir = new File("/tmp/Scala/WritePlainTextFile")
    isDirExists
    dir.mkdirs()
    isDirExists

    val filePath = new File(dir, "simpleFile")

    {
      val bufferedWriter = new BufferedWriter(new FileWriter(filePath))
      for(_ <- 1 to 10) {
        bufferedWriter.append("line\n")
      }
      bufferedWriter.close()
    }

    {
      val fileSource = scala.io.Source.fromFile(filePath)
      fileSource.getLines().foreach(println)
    }
  }

}
