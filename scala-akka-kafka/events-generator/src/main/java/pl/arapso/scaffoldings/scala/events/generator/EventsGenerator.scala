package pl.arapso.scaffoldings.scala.events.generator

import java.time.Instant
import java.util.UUID

object EventsGenerator extends App {

  case class Bid(price: Long, currency: String)

  case class Device(category: String)

  case class Event(event: String,
                   ts: Long,
                   bidId: String,
                   campaignId: String,
                   creativeId: String,
                   bid: Bid,
                   deviceId: String,
                   device: Device)

  def generate(): Event = {
    val r = scala.util.Random
    def random[T](avail: Seq[T]): T = avail(r.nextInt(avail.length))
    def event = random(Seq("WIN", "CLICK"))
    def ts = Instant.now().toEpochMilli
    def bidId = UUID.randomUUID().toString
    def campaignId = random(Seq("111", "222", "333", "444", "555"))
    def creativeId(campaignId: String) = campaignId + random(Seq("11", "12", "13"))
    def bid = Bid(Math.abs(r.nextLong() % 1000), random(Seq("EUR", "USD", "PLN")))
    def deviceId = UUID.randomUUID().toString
    def device = Device(random(Seq("1", "2", "3")))

    val campId = campaignId
    Event(event = event,
      ts = ts,
      bidId = bidId,
      campaignId = campId,
      creativeId = creativeId(campId),
      bid = bid,
      deviceId = deviceId,
      device = device)
  }

  println(generate())
}
