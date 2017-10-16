package it.gov.daf.dataquality.analysis

import org.scalatest.FunSuite

/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class EmailAnalyserTest extends FunSuite {

  val emailAnalyser = new EmailAnalyser

  //println(emailAnalyser.analyse("mbianchi@fub.it"))
//  println(emailAnalyser.analyse("mbianchi@fub.it"))
//  println(emailAnalyser.analyse("bianchi74@gmail.com"))

  //println(emailAnalyser.analyse("marco.bianchi@agid.gov.it"))
  //println(emailAnalyser.analyse("prot.gen.asl.vt.it@legalmail.it"))
  //println(emailAnalyser.analyse("aslumbria2@postacert.umbria.it"))
  println(emailAnalyser.analyse("affarigenerali.asprc@certificatamail.it"))

  //util.Map[String, String] analyse (emailAddress: String)  //test("Testing valid email addresses"){
    //assert(emailAnalyser.isValid("mbianchi@fub.it"))
    //assert(emailAnalyser.isValid("marco.bianchi@agid.gov.it"))


}
