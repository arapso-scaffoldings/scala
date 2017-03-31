package pl.arapso.scaffoldings.scala.custom.dates

import java.time.LocalDate
import java.time.temporal.ChronoUnit


object DatesSplitterExample extends App {

  val startDate = LocalDate.parse("2017-03-01")
  val endDate = LocalDate.parse("2017-02-28")

  val days = ChronoUnit.DAYS.between(startDate, endDate)

  (0 to days.toInt)
    .map(startDate.plusDays(_))
    .sliding(7, 7)
    .map(x => (x.head, x.last))
    .foreach(x => println(s"${x._1} ${x._2}"))


}
