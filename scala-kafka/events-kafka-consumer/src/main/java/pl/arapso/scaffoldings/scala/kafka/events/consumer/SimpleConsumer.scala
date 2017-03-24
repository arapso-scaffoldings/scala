package pl.arapso.scaffoldings.scala.kafka.events.consumer

import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.{ByteArrayDeserializer, StringDeserializer}


object SimpleConsumer extends App {

  implicit val system = ActorSystem("files-reader-sytem")
  implicit val materializer = ActorMaterializer()

  val consumerSettings = ConsumerSettings(system, new ByteArrayDeserializer, new StringDeserializer)
    .withBootstrapServers("localhost:9092")
    .withGroupId("group1")
    .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  val subscription = Subscriptions.topics("bids")

  val source = Consumer.plainSource(consumerSettings, subscription)

  val sink = Sink.foreach(println)

  val flow = source to sink

  flow.run()

}
