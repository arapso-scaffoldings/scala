package pl.arapso.scaffoldings.scala.kafka

import akka.stream.actor.ActorPublisher
import net.liftweb.json._
import pl.arapso.scaffoldings.scala.kafka.model.Event

class KafkaBidIdProducer extends ActorPublisher[String] {
  implicit val formats = DefaultFormats

  override def receive: Receive = {
    case EventMessage(line) =>
      val bidId = parse(line).extract[Event].bidId
      onNext(bidId)
  }

}
