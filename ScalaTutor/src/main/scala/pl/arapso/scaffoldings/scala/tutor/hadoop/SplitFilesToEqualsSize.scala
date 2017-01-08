package pl.arapso.scaffoldings.scala.tutor.hadoop

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, LocatedFileStatus, Path, RemoteIterator}
import org.apache.spark.SparkContext

import scala.collection.mutable.ListBuffer


object SplitFilesToEqualsSize {

  case class FileMeta(fileNo: Long, size: Long)

  case object FileMeta {
    def parse(iter: RemoteIterator[LocatedFileStatus]): FileMeta = {
      val temp = iter.next()
      FileMeta(getFileNo(temp.getPath.getName).toLong, temp.getLen)
    }
  }

  def main(args: Array[String]) {
    val conf = new Configuration()
    conf.set("fs.defaultFS", "hdfs://localhost")
    val rootDir = "/tmp"

    val hdfs = FileSystem.get(conf)
    val filesToMerge = ListBuffer[FileMeta]()
    val files = hdfs.listFiles(new Path(rootDir), false)
    while(files.hasNext) {
      filesToMerge += FileMeta.parse(files)
    }

    val maxSize = 50 * 1024l

    filesToMerge.sortBy(_.fileNo)
    val iter = filesToMerge.iterator
    while(iter.hasNext) {
      var currentSize = 0l
      val filesToMerge = ListBuffer[FileMeta]()
      while(currentSize < maxSize && iter.hasNext) {
        val nextFile = iter.next()
        currentSize += nextFile.size
        filesToMerge += nextFile
      }
      val leftFile = new Path(getFileName(filesToMerge.head))
      val tailFiles = filesToMerge.tail.map(x => new Path(getFileName(x))).toArray
      hdfs.concat(leftFile, tailFiles)
    }
  }

  def getFileName(fileMeta: FileMeta): String = {
    f"/tmp/part-${fileMeta.fileNo}%06d.txt"
  }

  val FileNameRgx = "part-[0]+(\\d+).txt"r
  def getFileNo(fileName: String): String = {
    fileName match {
      case FileNameRgx(no) => no;
      case _ => throw new IllegalArgumentException;
    }
  }

}
