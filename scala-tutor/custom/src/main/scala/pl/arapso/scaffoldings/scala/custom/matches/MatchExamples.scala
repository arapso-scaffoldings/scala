package pl.arapso.scaffoldings.scala.custom.matches

/**
  * Created by damian0o on 08.01.17.
  */
object MatchExamples {

  def regexMatchExample = {
    val matchedExample = "ips-00001.txt"
    val unmachedExample = "asd=-asd.txt"


    val FileNameRgx = "ips-[0]+(\\d+).txt"r
    def getFileNo(fileName: String): Long = {
      fileName match {
        case FileNameRgx(no) => no.toLong
        case _ => throw new IllegalArgumentException
      }
    }
    println(getFileNo(matchedExample))
    println(getFileNo(unmachedExample))
  }

  def main(args: Array[String]) {

  }


}
