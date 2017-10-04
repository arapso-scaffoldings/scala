package pl.arapso.scaffoldings.scala.custom.trycatch

import org.scalatest.{FlatSpec, Matchers}
import pl.arapso.scaffoldings.scala.custom.trycatch.TryCatchExamples.matchExample

import scala.util.Success

class TryCatchExamplesTest extends FlatSpec with Matchers {

  behavior of "Try catch examples"

  it should "return division result" in {
    val result = matchExample((Input("2"), Input("1")))
    result shouldBe 'successful
    result shouldBe Success(2)
  }

  it should "return that a is invalid" in {
    val result = matchExample((Input("invalid"), Input("23")))
    result shouldBe 'failure
  }

}
