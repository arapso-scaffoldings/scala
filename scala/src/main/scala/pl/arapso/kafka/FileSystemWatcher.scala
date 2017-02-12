package pl.arapso.kafka

import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{Files, Path, Paths}
import java.util.function.{BiPredicate, Consumer}

import akka.actor.Actor
import akka.routing.Router

case class StartWatching(path: String, router: Router)

case class StopWatching(path: String)

class FileSystemWatcher extends Actor {

  override def receive: Receive = {
    case StartWatching(path, router) => {
      val files = Files.find(Paths.get(path), 2, new BiPredicate[Path, BasicFileAttributes] {
        override def test(t: Path, u: BasicFileAttributes): Boolean = {
          return u.isRegularFile
        }
      })

      files.forEach(new Consumer[Path] {
        override def accept(t: Path): Unit = {
          router.route(IncomingFile(t), sender())
        }
      })
    }
    case StopWatching(path: String) => {

    }
  }
}
