package it.gov.daf.dataquality.analysis.email.checkers

import org.scalatest.FunSuite

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class FreeMailCheckerTest extends FunSuite {

  test("testGetInstance"){
    FreeMailChecker.getInstance()
  }

  test("testIsChecked") {
    val checker = FreeMailChecker.getInstance()
    assert(! checker.isChecked("someone@agid.gov.it"))
    assert(checker.isChecked("someone@gmail.com"))
  }

}
