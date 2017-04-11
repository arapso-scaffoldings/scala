package pl.arapso.scaffoldings.scala.custom.dates

import java.time.{LocalDate, Period}
import java.time.temporal.ChronoUnit.DAYS


object DatesSplitterExample extends App {

  def method1(startDate: LocalDate, endDate: LocalDate) = {
    val days = DAYS.between(startDate, endDate)
    (0 to days.toInt)
      .map(startDate.plusDays(_))
      .sliding(7, 7)
      .map(x => (x.head, x.last))
  }

  def method2(startDate: LocalDate, endDate: LocalDate) = {
    val days = DAYS.between(startDate, endDate)
    Iterable.iterate(startDate, days.toInt)(_.plusDays(1))
      .sliding(7, 7).map(x => (x.head, x.last))
  }

  def method3(startDate: LocalDate, endDate: LocalDate) = {
    val days = DAYS.between(startDate, endDate)

    val left = (0 to days.toInt).filter(_ % 7 == 0)
      .map(x => startDate.plusDays(x))

    val right = (0 to days.toInt)
      .filter(x => (x % 7 == 6) || (x == days.toInt))
      .map(x => startDate.plusDays(x))

    left.zip(right)
  }

  def method4(startDate: LocalDate, endDate: LocalDate) = {
    Stream.from(1).takeWhile(_ < 10)
  }

  def method5(startDate: LocalDate, endDate: LocalDate) = {
    val period = Period.ofDays(7)
    method5Worker(startDate, endDate, period)
  }

  def method5Worker(startDate: LocalDate, endDate: LocalDate, period: Period): List[(LocalDate, LocalDate)] = {
    if(startDate.plusDays(period.getDays).isAfter(endDate)) {
      List((startDate, endDate))
    } else {
      List((startDate, startDate.plusDays(period.getDays - 1))) ::: method5Worker(startDate.plusDays(period.getDays), endDate, period)
    }
  }

  val startDate = LocalDate.parse("2017-02-01")
  val endDate = LocalDate.parse("2017-01-28")

  method1(startDate, endDate).foreach(println)
  method2(startDate, endDate).foreach(println)
  method3(startDate, endDate).foreach(println)
  method4(startDate, endDate).foreach(println)
  method5(startDate, endDate).foreach(println)
}
