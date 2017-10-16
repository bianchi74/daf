package it.gov.daf.dataquality.discovery.web.subdomains

import org.scalatest.FunSuite

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class SubdomainsMinerTest extends FunSuite {


  test("test miner"){

    val subdomains = SubdomainsMiner.discoverSubdomains(("salute.gov.it"))
    println(subdomains)

  }

}
