package pl.arapso.scaffoldings.scala.custom.dates

import java.time.LocalDate

import org.scalatest.{FlatSpec, Matchers}
import pl.arapso.scaffoldings.scala.custom.dates.DatesSplitterExample._

/**
  * Test class for Dates Slitter Examples Method
  */
class DatesSplitterExampleTest extends FlatSpec with Matchers {

  type Input = (LocalDate, LocalDate) => List[DatesInterval]

  private def commonBase(input: Input) = {
    val startDate = LocalDate.of(2017, 8, 1)
    val endData = LocalDate.of(2017, 8, 31)

    val result = input(startDate, endData)

    result should {
      have length 5 and
      contain theSameElementsInOrderAs List(
        (LocalDate.of(2017, 8, 1), LocalDate.of(2017, 8, 7)),
        (LocalDate.of(2017, 8, 8), LocalDate.of(2017, 8, 14)),
        (LocalDate.of(2017, 8, 15), LocalDate.of(2017, 8, 21)),
        (LocalDate.of(2017, 8, 22), LocalDate.of(2017, 8, 28)),
        (LocalDate.of(2017, 8, 29), LocalDate.of(2017, 8, 31))
      )
    }
  }


  behavior of "Sliding method"

  it should "split date range int even interval" in {
    commonBase(slidingMethod)
  }

  behavior of "Zip method"

  it should "split date range int even interval" in {
    commonBase(zipMethod)
  }

  behavior of "Recursive method"

  it should "split date range int even interval" in {
    commonBase(recursiveMethod)
  }

  behavior of "Tick clock method"

  it should "split date range int even interval" in {
    commonBase(clockMethod)
  }

  behavior of "Iterable method"

  it should "generate intervals with iterator method" in {
    commonBase(DatesSplitterExample.iterateThrowDays)
  }

}
