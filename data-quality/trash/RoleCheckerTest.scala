package it.gov.daf.dataquality.analysis.email.checkers

import org.scalatest.FunSuite

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class RoleCheckerTest extends FunSuite {

  test("testGetInstance") {
    RoleChecker.getInstance()
  }

  test("testHasRole"){
    val checker = RoleChecker.getInstance()
    assert(checker.isChecked("info@"))
    assert(!checker.isChecked("notARole@"))
  }

}
