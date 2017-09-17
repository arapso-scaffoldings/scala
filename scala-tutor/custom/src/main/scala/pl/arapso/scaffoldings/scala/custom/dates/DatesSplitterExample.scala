package pl.arapso.scaffoldings.scala.custom.dates

import java.time._
import java.time.temporal.ChronoUnit.DAYS

/**
  * Examples of codes for splitting big interval into several smaller interval.
  *
  * Examples of usage:
  * Split big query to database with bunch of smaller queries.
  */
object DatesSplitterExample extends App {

  type DatesInterval = (LocalDate, LocalDate)

  def slidingMethod(startDate: LocalDate, endDate: LocalDate): List[DatesInterval] = {
    val days = DAYS.between(startDate, endDate)
    (0L to days).
      map(startDate.plusDays(_)).
      sliding(7, 7).
      map(x => (x.head, x.last)).
      toList
  }

  def zipMethod(startDate: LocalDate, endDate: LocalDate): List[DatesInterval] = {
    val days = DAYS.between(startDate, endDate)

    val left = (0 to days.toInt).filter(_ % 7 == 0)
      .map(x => startDate.plusDays(x))
    val right = (0 to days.toInt)
      .filter(x => (x % 7 == 6) || (x == days.toInt))
      .map(x => startDate.plusDays(x))
    left.
      zip(right).
      toList
  }

  def recursiveMethod(startDate: LocalDate, endDate: LocalDate): List[DatesInterval] = {
    val period = Period.ofDays(7)
    method5Worker(startDate, endDate, period)
  }

  def method5Worker(startDate: LocalDate, endDate: LocalDate, period: Period): List[(LocalDate, LocalDate)] = {
    if (startDate.plusDays(period.getDays).isAfter(endDate)) {
      List((startDate, endDate))
    } else {
      List((startDate, startDate.plusDays(period.getDays - 1))) ::: method5Worker(startDate.plusDays(period.getDays), endDate, period)
    }
  }


  def clockMethod(startDate: LocalDate, endDate: LocalDate): List[DatesInterval] = {
    val duration = Duration.ofDays(7)

    val start = Clock.fixed(startDate.atStartOfDay().toInstant(ZoneOffset.UTC), ZoneId.systemDefault())

    val temp: Clock = Clock.tick(Clock.system(ZoneId.systemDefault()), duration)
    val date = LocalDate.now(temp)
    List((date, date.plusDays(6)))
  }


  def iterateThrowDays(startDate: LocalDate, endDate: LocalDate): List[DatesInterval] = {

    def getNext(step: Int)(): LocalDate = {
      startDate.plusDays(step)
    }

    Stream.continually(getNext(7)).
      takeWhile(_.isAfter(endDate)).
      map(date => (date, date.plusDays(6))).
      toList
  }
}
