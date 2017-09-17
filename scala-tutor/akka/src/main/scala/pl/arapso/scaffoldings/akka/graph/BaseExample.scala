package pl.arapso.scaffoldings.akka.graph

import akka.pattern.pipe
import akka.{Done, NotUsed}
import akka.actor.{Actor, ActorRef, ActorSystem, Props, Status}
import akka.stream._
import akka.stream.scaladsl.{Flow, GraphDSL, RunnableGraph, Sink, Source}

import concurrent.duration._
import scala.concurrent.{Await, Future}

case class EndOfProcessing(msg: String)

class Monitor() extends Actor {
  import context.dispatcher

  override def receive: Receive = {
    case EndOfProcessing(reson) => {
      println(s"Shutting down system: reson $reson")
      val systemHalted = context.system.terminate()
      Await.result(systemHalted, 2 second)
      println("Bye")
    }
    case Done => {
      println("stream completed")
      context.system.scheduler.scheduleOnce(3 second, self, EndOfProcessing("Stream ended"))
    }
    case Status.Failure(e) => println("stream failed: ${e.getMessage}")
    case x => println(s"Not known status $x")
  }
}

trait BaseExample[T] {
  import GraphDSL.Implicits._

  implicit val system = ActorSystem()
  implicit val materialize = ActorMaterializer()

  val monitor: ActorRef = system.actorOf(Props[Monitor])

  def source: Source[Int, NotUsed] = {
    Source(1 to 10)
  }

  def out: Sink[Int, Future[T]]

  def shape: Graph[FlowShape[Int, Int], NotUsed]

  def execute(x : T => Unit) : Future[T] = {
    import system.dispatcher
    val watchedFlow = Flow[Int].watchTermination()((_, f) => f pipeTo monitor)
    val in = source
    val flow: Future[T] = in.via(watchedFlow).via(shape).runWith(out)
    flow.map(f => {
      x(f);
      f
    })
  }

}
