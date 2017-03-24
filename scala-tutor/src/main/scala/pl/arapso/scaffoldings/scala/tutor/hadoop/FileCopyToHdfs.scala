package pl.arapso.scaffoldings.scala.tutor.hadoop

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

import scala.collection.mutable.ListBuffer

/**
  * Created by damian0o on 08.01.17.
  */
object FileCopyToHdfs {


  def main(args: Array[String]) {
    copyAllFilesToHdfs("/tmp/scala/IpGenerator/", "/tmp")
  }

  def copyAllFilesToHdfs(src: String, dst: String) = {
    val conf = new Configuration()
    conf.set("fs.defaultFS", "hdfs://localhost")
    val fs = FileSystem.get(conf)
    val localFs = FileSystem.get(new Configuration())

    val filesToCopy = ListBuffer[Path]()
    val localFiles = localFs.listFiles(new Path(s"file://$src"), false)
    while(localFiles.hasNext) {
      filesToCopy += localFiles.next().getPath
    }
    filesToCopy.foreach(localFile => {
      fs.copyFromLocalFile(localFile, new Path(dst, localFile.getName))
    })
  }

}
