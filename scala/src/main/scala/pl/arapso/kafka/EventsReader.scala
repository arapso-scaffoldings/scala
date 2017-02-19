package pl.arapso.kafka

import java.nio.file.Path

import akka.actor.{Actor, ActorRef}

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

case class Register(actor: ActorRef)

/**
  * Read logs line and send it do registered actors
  *
  */
class EventsReader extends Actor {

  var actors = ListBuffer[ActorRef]()

  case class Tick(n: Int)

  val reportDuration = 3.second

  context.system.scheduler.scheduleOnce(reportDuration, self, Tick(1))

  var readLines = 0

  override def receive: Receive = {
    case Register(actor) => {
      actors += actor
    }
    case IncomingFile(filePath: Path) => {
      scala.io.Source.fromFile(filePath.toFile).getLines().foreach(line => {
        readLines += 1
        actors.foreach(a => {
          a ! EventMessage(line)
        })
      })
    }
    case Tick(n) => {
      println(s"[EventReader] Read lines $readLines)")
      context.system.scheduler.scheduleOnce(reportDuration, self, Tick(n + 1))
    }
  }
}
