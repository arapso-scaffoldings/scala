package pl.arapso.scaffoldings.akka.redis

import java.nio.file.{Path, Paths}

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl.{FileIO, Flow, Framing, GraphDSL, RunnableGraph, Sink, Source}
import akka.stream.{ActorMaterializer, ClosedShape}
import akka.util.ByteString
import redis.RedisClient

object RedisClientExample {

  def main(args: Array[String]): Unit = {
    val startTime = java.lang.System.currentTimeMillis()
    println(startTime)

    case class ProcessorResult(path: Path)
    implicit val system = ActorSystem()
    implicit val materlialize = ActorMaterializer()


    val redis = RedisClient()

    val file = Flow[ProcessorResult].map(_.path).flatMapConcat(FileIO.fromPath(_))
    val lines: Flow[ByteString, String, NotUsed] = Framing.delimiter(ByteString("\n"), 8192, allowTruncation = true).map(_.utf8String)

    val entries = Flow[String].mapConcat(_.split(":").toList)
    val sendToRedis = Flow[String].mapAsync[Long](2)(x => {
      redis.sadd("ad", x)
    })


    val result = ProcessorResult(Paths.get("/Users/damian/tmp/gen_cookies_file/result.txt"))
    val source = Source.single(result)

    RunnableGraph.fromGraph(GraphDSL.create() { implicit b =>
      import GraphDSL.Implicits._
      val file = Flow[ProcessorResult].map(_.path).flatMapConcat(FileIO.fromPath(_))
      val lines: Flow[ByteString, String, NotUsed] = Framing.delimiter(ByteString("\n"), 8192, allowTruncation = true).map(_.utf8String)
      val entries = Flow[String].mapConcat(_.split(":").toList)
      val sendToRedis = Flow[String].mapAsync[Long](32)(x => {
        redis.sadd("ad", x)
      })
      source ~> file ~> lines ~> entries ~> sendToRedis ~> Sink.ignore
      ClosedShape
    }).run()

  }

}
