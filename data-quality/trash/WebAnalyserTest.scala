package it.gov.daf.dataquality.analysis

import org.scalatest.FunSuite

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class WebAnalyserTest extends FunSuite {

  private val analyser = new WebAnalyser()

  test("testAnalyse - testing valid URLs") {

    // Syntactically correct url. Not existing server
    val test_valid_01 = analyser.analyse("http://www.teamdigitale.gov.it")
    assert(test_valid_01 .get("url") == "http://www.teamdigitale.gov.it")
    assert(test_valid_01 .get("syntax") == "True")
    assert(test_valid_01 .get("server") == "False")

    print (test_valid_01)

  }

  test("testAnalyse - testing invalid URLs"){

    // Syntactically incorrect url
    val test_invalid_01 = analyser.analyse("www.teamdigitale.gov.it")
    assert(test_invalid_01.get("url") == "www.teamdigitale.gov.it")
    assert(test_invalid_01.get("syntax") == "False")
    assert(test_invalid_01.get("server") == "False")

    print (test_invalid_01)

  }

}
