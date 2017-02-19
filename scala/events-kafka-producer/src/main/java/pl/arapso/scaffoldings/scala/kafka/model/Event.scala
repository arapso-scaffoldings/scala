package pl.arapso.scaffoldings.scala.kafka.model

case class Bid(currency: String, price: Double)

case class Event(bidId: String, bid: Bid)
