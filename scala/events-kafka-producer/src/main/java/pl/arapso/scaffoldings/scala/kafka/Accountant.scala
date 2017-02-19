package pl.arapso.scaffoldings.scala.kafka

import akka.actor.Actor
import net.liftweb.json._
import pl.arapso.scaffoldings.scala.kafka.model.Event

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class Accountant extends Actor {
  implicit val formats = DefaultFormats

  case class AccountantEvent(bidId: String)

  case class Tick(n: Int)

  val reportDuration = 3.second

  context.system.scheduler.scheduleOnce(reportDuration, self, Tick(1))

  var eventsNo: Long = 0l
  var totalAmount: Double = 0d
  var lastBidId: String = ""

  override def receive: Receive = {
    case EventMessage(line) => {
      val event = parse(line).extract[Event]
      lastBidId = event.bidId
      totalAmount += event.bid.price
      eventsNo += 1
    }

    case Tick(i) => {
      println(s"Accountant have processed $eventsNo [sum=$totalAmount,lasatBidId=$lastBidId]")
      context.system.scheduler.scheduleOnce(reportDuration, self, Tick(i + 1))
    }
  }
}
