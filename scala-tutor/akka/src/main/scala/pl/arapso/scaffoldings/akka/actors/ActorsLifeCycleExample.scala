package pl.arapso.scaffoldings.akka.actors

import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

case object TickMessage

final case class CustomMessage(content: String)

final case class CreateTicker(content: String)

class ChildTickActor extends Actor {

  override def receive: Receive = {
    case x => println("Tick occurred.")
  }
}

class CountingActor extends Actor {

  var counter = 1

  override def preStart(): Unit = {
    println("Pre start")
    super.preStart()
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Pre restart")
    super.preRestart(reason, message)
  }

  override def receive: Receive = {
    case x: String => {
      println(s"Counter $counter, msg: $x")
      counter += 1
      if (counter == 3) {
        throw new Exception("Some error")
      }
    }

    case showCounter: CustomMessage => {
      println(s"Debug Counter value is $counter. MSG: '${showCounter.content}'")
    }

    case createTicker: CreateTicker => {
      println("Creating new tickActor")
      val tickActor = context.actorOf(Props(new ChildTickActor))
      context.system.scheduler.schedule(0 second,1 second, tickActor, TickMessage)
    }
  }
}


object ActorsLifeCycleExample extends App {

  val system = ActorSystem()

  val actor = system.actorOf(Props(new CountingActor()))

  actor ! CreateTicker("as")

  Thread.sleep(3000)


  actor ! "HEJ 1"
  Thread.sleep(3000)

  actor ! CustomMessage("Hej i am Case class msg")

  Thread.sleep(3000)
  actor ! "HEJ 2"

  Thread.sleep(3000)
  actor ! "HEJ 3"

  Thread.sleep(3000)
  actor ! CustomMessage("Hej i am Case class msg")
  Thread.sleep(3000)
  actor ! "HEJ 4"
  Thread.sleep(3000)
  actor ! "HEJ 5"

  Thread.sleep(10000)
  system.terminate()

}
