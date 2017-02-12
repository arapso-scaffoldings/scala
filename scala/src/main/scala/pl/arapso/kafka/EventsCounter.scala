package pl.arapso.kafka

import akka.actor.Actor
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global


class EventsCounter extends Actor {

  case class Tick(n: Int)

  context.system.scheduler.scheduleOnce(1.second, self, Tick(1))

  var eventsNo = 0

  override def receive: Receive = {
    case Event(line) =>
      eventsNo += 1
    case Tick(i) =>
      println(s"Events no $eventsNo")
      context.system.scheduler.scheduleOnce(1.second, self, Tick(i + 1))
  }
}
