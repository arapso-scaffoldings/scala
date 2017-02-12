package pl.arapso.kafka

import akka.actor.Actor
import com.google.gson.{Gson, JsonParser}

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
      lastBidId = getBidId(line)
    }
    case Tick(i) => {
      println(s"Accountant have $totalAmount messages with last BidId $lastBidId")
      context.system.scheduler.scheduleOnce(reportDuration, self, Tick(i + 1))
    }
  }

  def getBidId(line: String): String = {
    val jsonAst = new JsonParser().parse(line)
    jsonAst.getAsJsonObject.get("bidId").getAsString
  }
}
