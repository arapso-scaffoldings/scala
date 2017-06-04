package pl.arapso.scaffoldings.akka.redis

import java.nio.file.{Path, Paths}

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{FileIO, Flow, Framing, GraphDSL, Sink, Source}
import akka.util.ByteString
import redis.RedisClient

object RedisClientExample {

  def main(args: Array[String]): Unit = {
    case class ProcessorResult(path: Path)
    implicit val system = ActorSystem()
    implicit val materlialize = ActorMaterializer()
    import system.dispatcher

    val redis = RedisClient()

    val file = Flow[ProcessorResult].map(_.path).flatMapConcat(FileIO.fromPath(_))
    val lines: Flow[ByteString, String, NotUsed] = Framing.delimiter(ByteString("\n"), 8192, allowTruncation = true).map(_.utf8String)

    val entries = Flow[String].mapConcat(_.split(":").toList)
    val sendToRedis = Flow[String].mapAsync[Long](2)(x => {
      redis.sadd("ad", x)
    })

    val result = ProcessorResult(Paths.get(RedisClientExample.getClass.getResource("example.txt").getPath))
    val source = Source.single(result)

    val flow = file.via(lines).via(entries).via(sendToRedis)

    val toRun = source.via(flow).runWith(Sink.ignore)

    toRun.onComplete(x => {
      system.terminate()
    })
  }

}
