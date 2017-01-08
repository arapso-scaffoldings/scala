package pl.arapso.scaffoldings.scala.tutor.spark

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.SparkContext


object SplitFilesToEqualsSize {

  case class FileMeta(name: String, size: Long)

  def main(args: Array[String]) {
    val sc = SparkContext.getOrCreate()
    val hdfs = FileSystem.get(sc.hadoopConfiguration)
    val files = hdfs.listFiles(new Path("/tmp"), false)
    val setOfFiles = scala.collection.mutable.ListBuffer[FileMeta]()
    while(files.hasNext) {
      val nextFile = files.next()
      setOfFiles += FileMeta(nextFile.getPath.getName, nextFile.getLen)
    }
    setOfFiles.foreach(println)
  }

}
