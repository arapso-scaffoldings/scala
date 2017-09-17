package pl.arapso.scaffoldings.akka.upload

import java.io._
import java.util.zip.{GZIPInputStream, GZIPOutputStream}

import scala.util.Try

object UploadImage extends App {

  def getFileOutputStream(path: String) = {
    val dstFile = new File(path)
    if (dstFile.exists()) {
      if (dstFile.isFile) {
        dstFile.delete()
      } else {
        throw new Exception("Result file already exists")
      }
    }
    new ObjectOutputStream(new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(dstFile))))
  }

  def getFileInputStream(path: String) = {
    new ObjectInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(path))))
  }

  final case class User(name: String, password: String, age: Double)

  final case class TestUser(name: String, password: String, age: Double)

  val path = "/home/damian0o/tmp/uploader/objects/user.bin"

  val outputStream = getFileOutputStream(path)
  outputStream.writeObject(User("damian", "123qwe", 12.1))
  outputStream.writeObject(User("piotr", "321ewq", 15.12))
  outputStream.close()

  val inputStream = getFileInputStream(path)

  def reader[T](inputStream: ObjectInputStream)(): Option[T] = {
    val mbyObject = Try {
      inputStream.readObject()
    }
    mbyObject.toOption.map(_.asInstanceOf[T])
  }

  def readNext = reader[User](inputStream)

  Iterator.continually(readNext).foreach(println)

  println("END")
}
