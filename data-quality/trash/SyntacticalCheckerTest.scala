package it.gov.daf.dataquality.analysis.email.checkers

import org.scalatest.FunSuite

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class SyntacticalCheckerTest extends FunSuite {

  val checker = SyntacticalChecker.getInstance

  /*
   * SYNTACTICAL CHECKS see RFC2822
   * http://codefool.tumblr.com/post/15288874550/list-of-valid-and-invalid-email-addresses
   */

  // TRUE-Positive
  test("Testing valid email addresses"){
    assert(checker.isChecked("email@example.com"))
    assert(checker.isChecked("firstname.lastname@example.com"))
    assert(checker.isChecked("email@subdomain.example.com"))
    assert(checker.isChecked("firstname+lastname@example.com"))
    //assert(checker.isChecked("email@123.123.123.123")) ???
    assert(checker.isChecked("email@[123.123.123.123]"))
    assert(checker.isChecked("\"email\"@example.com"))
    assert(checker.isChecked("1234567890@example.com"))
    assert(checker.isChecked("email@example-one.com"))
    assert(checker.isChecked("_______@example.com"))
    assert(checker.isChecked("email@example.name"))
    assert(checker.isChecked("email@example.museum"))
    assert(checker.isChecked("email@example.co.jp"))
    assert(checker.isChecked("firstname-lastname@example.com"))
    assert(checker.isChecked("あいうえお@example.com"))
  }

  test("Testing strange valid email addresses"){
    assert(checker.isChecked("much.\"more\\ unusual\"@example.com"))
    assert(checker.isChecked("very.unusual.\"@\".unusual.com@example.com"))
  }

  // TRUE-NEGATIVE
  test("Testing invalid email addresses"){
    assert(! checker.isChecked("plainaddress"))
    assert(! checker.isChecked("#@%^%#$@#$@#.com"))
    assert(! checker.isChecked("@example.com"))
    assert(! checker.isChecked("Joe Smith <email@example.com>"))
    assert(! checker.isChecked("email.example.com"))
    assert(! checker.isChecked("email@example@example.com"))
    assert(! checker.isChecked(".email@example.com"))
    assert(! checker.isChecked("email.@example.com"))
    assert(! checker.isChecked("email..email@example.com"))
    assert(! checker.isChecked("email@example.com (Joe Smith)"))
    assert(! checker.isChecked("email@example"))
    assert(! checker.isChecked("email@-example.com"))
    assert(! checker.isChecked("email@example.web"))
    assert(! checker.isChecked("email@111.222.333.44444"))
    assert(! checker.isChecked("email@example..com"))
    assert(! checker.isChecked("Abc..123@example.com"))
    assert(! checker.isChecked("me@"))
    assert(! checker.isChecked("me.@example.com"))
    assert(! checker.isChecked("me@example..com"))
    assert(! checker.isChecked("me.example@com"))
    assert(! checker.isChecked("me\\@example.com"))
  }

  test("Testing strange invalid email addresses"){
    assert(! checker.isChecked("\"(),:;<>[\\]@example.com"))
    assert(! checker.isChecked("just\"not\"right@example.com"))
    assert(! checker.isChecked("this\\ is\"really\"not\\allowed@example.com"))
  }

}
