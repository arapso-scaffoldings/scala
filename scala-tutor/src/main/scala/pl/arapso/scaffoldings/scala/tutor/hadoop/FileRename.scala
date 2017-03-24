package pl.arapso.scaffoldings.scala.tutor.hadoop

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

/**
  * Created by damian0o on 08.01.17.
  */
object FileRename {

  def main(args: Array[String]) {
    val conf = new Configuration()
    conf.set("fs.defaultFS", "hdfs://localhost")
    renameAllFilesInDir("/tmp", conf)
  }

  def renameAllFilesInDir(src: String, conf: Configuration): Unit = {
    val fs = FileSystem.get(conf)
    val files = fs.listFiles(new Path(src), false)
    var i = 1
    while(files.hasNext) {
      val fileStatus = files.next()
      val src = fileStatus.getPath
      val dst = new Path(fileStatus.getPath.getParent, s"DE_2016-12-01_$i.txt")
      println(s"Move file ${src.toString} to ${dst.toString}")
      fs.rename(src, dst)
      i = i + 1
    }
  }

}
