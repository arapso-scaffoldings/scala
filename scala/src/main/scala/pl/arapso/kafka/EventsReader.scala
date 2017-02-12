package pl.arapso.kafka

import java.nio.file.Path

import akka.actor.{Actor, ActorRef}

import scala.collection.mutable.ListBuffer

case class Register(actor: ActorRef)

/**
  * Read logs line and send it do registered actors
  *
  */
class EventsReader extends Actor {

  var actors = ListBuffer[ActorRef]()

  override def receive: Receive = {
    case Register(actor) => {
      actors += actor
    }
    case IncomingFile(filePath: Path) => {
      scala.io.Source.fromFile(filePath.toFile).getLines().foreach(line => {
        actors.foreach(a => {
          a ! Event(line)
        })
      })
    }
  }
}
