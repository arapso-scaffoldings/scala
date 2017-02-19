package pl.arapso.kafka

import java.nio.file.Path
import akka.actor.Actor

/**
  * Class responsible for reading source path line by line
  */
class FileLinesCounter() extends Actor {
  var linesNo = 0

  def read(filePath: Path): Int = {
    scala.io.Source.fromFile(filePath.toFile).getLines().map((_) => 1).sum
  }

  override def receive: Receive = {
    case IncomingFile(path) =>
      linesNo += read(path)
  }
}
