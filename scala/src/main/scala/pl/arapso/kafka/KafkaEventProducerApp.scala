package pl.arapso.kafka

import akka.actor.{ActorSystem, Props}
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.routing.{ActorRefRoutee, BroadcastRoutingLogic, Router}
import akka.stream.ActorMaterializer
import akka.stream.actor.ActorPublisherMessage.Request
import akka.stream.scaladsl.Source
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}

object KafkaEventProducerApp {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("files-reader-sytem")

    implicit val materializer = ActorMaterializer()

    val fileLinesCounter = system.actorOf(Props(new FileLinesCounter()), name = "fileLinesCounter")
    val eventsReader = system.actorOf(Props(new EventsReader()), name = "eventsReader")

    val eventsCounter = system.actorOf(Props(new EventsCounter()), name = "eventsCounter")
    val accountant = system.actorOf(Props(new Accountant), name = "accountant")

    eventsReader ! Register(eventsCounter)
    eventsReader ! Register(accountant)

    val source = Source.actorPublisher[String](Props(new KafkaBidIdProducer)).
      map { elem =>
        new ProducerRecord[Array[Byte], String]("bids", elem)
      }

    val producerSettings = ProducerSettings(system, new ByteArraySerializer, new StringSerializer)
      .withBootstrapServers("localhost:9092")
    val sink = Producer.plainSink(producerSettings)
    val flow = source to sink
    val streamActor = flow.run()
    streamActor ! Request(Long.MaxValue)

    eventsReader ! Register(streamActor)
    val fileWatcher = system.actorOf(Props(new FileSystemWatcher()), name = "fileSystemWatcher")
    val router = {
      Router(BroadcastRoutingLogic(), Vector(
        ActorRefRoutee(fileLinesCounter),
        ActorRefRoutee(eventsReader)
      ))
    }
    fileWatcher ! StartWatching("/tmp/python/", router)
  }

}
