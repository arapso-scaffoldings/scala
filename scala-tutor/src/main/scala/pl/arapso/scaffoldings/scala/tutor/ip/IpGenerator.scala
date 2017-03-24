package pl.arapso.scaffoldings.scala.tutor.ip

import java.io.{BufferedWriter, File, FileWriter}

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

import scala.util.Random

object App {
  val MinSize: Int = 1 * 1024
  val MaxSize: Int = 10 * 1024

  def main(args: Array[String]) {
    for(i <- 1 to 100) genIpFile(i)

  }

  def genIpFile(no: Int) = {
    val maxFileSize = fileSize
    println(s"Bytes to write $maxFileSize")
    val writer = new IpFileWriter(f"/tmp/scala/IpGenerator/part-$no%06d.txt", maxFileSize)
    while(writer << IpGenerator.genIp){}
    writer.close
  }

  def fileSize(): Long = {
    scala.util.Random.nextInt(MaxSize - MinSize) + MinSize
  }
}

object IpGenerator {
  private val UpperBoundIpOctetNumber = 256
  def genIp: String =  {
    for(_ <- 1 to 4)
      yield Random.nextInt(UpperBoundIpOctetNumber).toString
  }.mkString(".")

}
//    write some comment to
class IpFileWriter(filePath: String, maxFileSize: Long) {

  private val tempFile = new File(filePath)
  private val fileWriter: BufferedWriter = create;
  private var wroteBytes: Long = 0l

  def create = {
    val dir = new File(tempFile.getParent)
    if(!dir.exists) dir.mkdirs()
    new BufferedWriter(new FileWriter(tempFile))
  }

  def << (ip: String): Boolean = {
    fileWriter.append(ip)
    fileWriter.newLine()

    wroteBytes += ip.getBytes.length + 1
    wroteBytes < maxFileSize
  }

  def close = {
    fileWriter.close()
  }

  def addFileSizeToFileName = {
    val newFilePrefix = filePath.substring(0, filePath.lastIndexOf("."))
    val newFileExtension = filePath.substring(filePath.lastIndexOf("."), filePath.length)
    val oldFile = new File(filePath)
    val newFile = new File(s"$newFilePrefix-$wroteBytes$newFileExtension")
    oldFile.renameTo(newFile)
  }

}