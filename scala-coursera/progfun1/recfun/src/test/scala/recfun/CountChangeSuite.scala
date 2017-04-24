package recfun

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CountChangeSuite extends FunSuite {
  import Main.countChange

  test("countChange: one coin available") {
    assert(countChange(4, List(2)) === 1)
    assert(countChange(4, List(1)) === 1)
    assert(countChange(4, List(3)) === 0)
    assert(countChange(6, List(3)) === 1)
  }

  test("countChange: example given in instructions") {
    assert(countChange(4,List(1,2)) === 3)
    assert(countChange(6,List(1,2)) === 4)
    assert(countChange(8, List(2, 4)) === 3)
  }

  test("countChange: for more coins") {
    assert(countChange(3, List(1, 2, 3)) === 3)
    assert(countChange(12, List(2, 3, 4)) === 7)
  }

  test("countChange: sorted CHF") {
    assert(countChange(300,List(5,10,20,50,100,200,500)) === 1022)
  }

  test("countChange: no pennies") {
    assert(countChange(301,List(5,10,20,50,100,200,500)) === 0)
  }

  test("countChange: unsorted CHF") {
    assert(countChange(300,List(500,5,50,100,20,200,10)) === 1022)
    assert(countChange(300,List(500,50,5,100,200,10,20)) === 1022)
  }
}
