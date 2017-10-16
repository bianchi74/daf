package it.gov.daf.dataquality.analysis.email.checkers

import org.scalatest.FunSuite

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class DisposableCheckerTest extends FunSuite {

  test("testIsChecked") {

    val checker = DisposableChecker.getInstance();
    assert(checker.isChecked("username@despam.it"))
    assert(! checker.isChecked("username@notDisposable.it"))
  }

}
