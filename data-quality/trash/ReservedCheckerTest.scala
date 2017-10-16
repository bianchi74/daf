package it.gov.daf.dataquality.analysis.email.checkers

import org.scalatest.FunSuite

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class ReservedCheckerTest extends FunSuite {

  test("getInstance"){
    ReservedChecker.getInstance();
  }

  test("testIsChecked") {
    val checker = ReservedChecker.getInstance()
    assert(checker.isChecked("webmaster@"));
    assert(! checker.isChecked("notReserved@"));
  }

}
