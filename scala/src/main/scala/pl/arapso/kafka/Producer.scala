package pl.arapso.kafka

import akka.actor.{ActorSystem, Props}
import akka.routing.{ActorRefRoutee, BroadcastRoutingLogic, Router}

object Producer {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("files-reader-sytem")

    val fileLinesCounter = system.actorOf(Props(new FileLinesCounter()), name = "fileLinesCounter")
    val eventsReader = system.actorOf(Props(new EventsReader()), name = "eventsReader")

    val eventsCounter = system.actorOf(Props(new EventsCounter()), name = "eventsCounter")
    val accountant = system.actorOf(Props(new Accountant), name = "accountant")

    eventsReader ! Register(eventsCounter)
    eventsReader ! Register(accountant)

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
