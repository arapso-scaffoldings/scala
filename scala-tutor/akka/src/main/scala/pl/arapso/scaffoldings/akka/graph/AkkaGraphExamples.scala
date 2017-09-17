package pl.arapso.scaffoldings.akka.graph


import akka.stream._
import akka.stream.scaladsl.{Flow, GraphDSL, Sink}
import akka.{Done, NotUsed}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object AkkaGraphExamples {
  def main(args: Array[String]) {
    def run[T](example: BaseExample[T]) = {
      example.execute(res => {
        println(s"Result $res")
      })
    }
    val result: Future[Int] = run(new SinkSumExample)
    Await.ready(result, 3 seconds)
  }
}

class ThrottleExample() extends BaseExample[Done] {
  def out = Sink.foreach(println)

  def shape: Graph[FlowShape[Int, Int], NotUsed] = GraphDSL.create() { implicit builder =>
    builder.add(Flow[Int].initialDelay(2 seconds).throttle(5, 1 seconds, 1, ThrottleMode.shaping))
  }
}

class SumExample extends BaseExample[Done] {
  def out = Sink.foreach(println)
  override def shape: Graph[FlowShape[Int, Int], NotUsed] = GraphDSL.create() { implicit b =>
    b.add(Flow[Int].fold(0)(_ + _))
  }
}

class SinkSumExample extends BaseExample[Int] {
  def out = Sink.fold[Int, Int](0)(_ + _)

  override def shape: Graph[FlowShape[Int, Int], NotUsed] = GraphDSL.create() { implicit b =>
    b.add(Flow[Int].filter(_ % 2 == 0))
  }
}
