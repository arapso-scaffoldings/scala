package pl.arapso.scaffoldings.akka.graph

import akka.actor.{Actor, ActorSystem, Props, Status}
import akka.pattern.pipe
import akka.stream.scaladsl.{Flow, GraphDSL, RunnableGraph, Sink, Source}
import akka.stream._
import akka.{Done, NotUsed}

import scala.concurrent.Await
import scala.concurrent.duration._

object StreamExample {

  case class EndOfProcessing(msg: String)

  class Monitor() extends Actor {
    import context.dispatcher

    override def receive: Receive = {

      case EndOfProcessing(reson) => {
        println(s"Shutting down system: reson $reson")
        val systemHalted = context.system.terminate()
        Await.result(systemHalted, 3 second)
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

  def main(args: Array[String]) {
    implicit val system = ActorSystem()
    implicit val materlialize = ActorMaterializer()
    import system.dispatcher

    val monitor = system.actorOf(Props[Monitor])

    import GraphDSL.Implicits._

    val shape: Graph[FlowShape[Int, Int], NotUsed] = GraphDSL.create() { implicit builder =>
      println("Hello Graph World")
      val delayedStream = builder.add(Flow[Int].watchTermination()((_, f) => f pipeTo monitor))
      val watchedFlow =  builder.add(Flow[Int].initialDelay(2 second).throttle(5, 1 second, 1, ThrottleMode.shaping))
      delayedStream ~> watchedFlow
      FlowShape(delayedStream.in, watchedFlow.out)
    }

    val out = Sink.foreach(println)

    val desc = GraphDSL.create(shape, out)(_) { implicit builder => (shape, out) =>
      val in = builder.add(Source(1 to 10))
      in ~> shape ~> out
      ClosedShape
    }

    val g = RunnableGraph.fromGraph(desc)

    val result: NotUsed = g.run()

  }

}
