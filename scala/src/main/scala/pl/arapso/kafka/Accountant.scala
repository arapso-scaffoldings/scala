package pl.arapso.kafka

import akka.actor.Actor
import net.liftweb.json._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class Accountant extends Actor {

  case class AccountantEvent(bidId: String)

  case class Tick(n: Int)

  val reportDuration = 3.second

  context.system.scheduler.scheduleOnce(reportDuration, self, Tick(1))

  var totalAmount: Double = 0d

  var lastBidId: String = ""

  override def receive: Receive = {
    case Event(line) => {
      totalAmount += 1
      val jsonAst = parse(line)
      val map = (jsonAst \\ "bidId").values
      lastBidId = map.get("bidId").getOrElse("").toString
    }
    case Tick(i) => {
      println(s"Accountant have $totalAmount messages with last BidId $lastBidId")
      context.system.scheduler.scheduleOnce(reportDuration, self, Tick(i + 1))
    }
  }
}
