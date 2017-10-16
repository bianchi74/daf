package it.gov.daf.dataquality.analysis.email.smtp

import it.gov.daf.dataquality.analysis.email.smtp.commands._
import org.scalatest.{BeforeAndAfterEach, FunSuite}


/**
  * @author Marco Bianchi (bianchi74@gmail.com)
  */
class SmtpClientTest extends FunSuite with BeforeAndAfterEach {

  val smtpClient = new SmtpClient;

  override def beforeEach() {
    assert(smtpClient.connect("mail.fub.it"))
  }

  override def afterEach() {
    assert(smtpClient.disconnect())
  }

  test("Testing connection") {
    // Just testing the connection
  }

  test("EHLO command") {
    val ehloCmd = new Ehlo("mail.fub.it")
    assert(smtpClient.sendCommand(ehloCmd))
    assert(ehloCmd.getResulCode == ehloCmd.getSuccessResponseCode)
  }

  test("HELO command") {
    val heloCmd = new Helo("mail.fub.it")
    assert(smtpClient.sendCommand(heloCmd ))
    assert(heloCmd.getResulCode == heloCmd.getSuccessResponseCode)
  }

  test("MAIL-FROM command"){
    val ehloCmd = new Ehlo("mail.fub.it")
    assert(smtpClient.sendCommand(ehloCmd))
    val mailFrom = new MailFrom("mbianchi@fub.it")
    assert(smtpClient.sendCommand(mailFrom))
    assert(mailFrom.getResulCode == mailFrom.getSuccessResponseCode)
  }

  test("RCPT-TO command"){
    val ehloCmd = new Ehlo("mail.fub.it")
    assert(smtpClient.sendCommand(ehloCmd))
    val mailFrom = new MailFrom("mbianchi@fub.it")
    assert(smtpClient.sendCommand(mailFrom))
    assert(mailFrom.getResulCode == mailFrom.getSuccessResponseCode)
    val mailTo = new Rcpt("mbianchi@fub.it")
    assert(smtpClient.sendCommand(mailTo))
    assert(mailTo.getResulCode == mailTo.getSuccessResponseCode)
  }

  test("VRFY disabled"){
    val ehloCmd = new Ehlo("mail.fub.it")
    assert(smtpClient.sendCommand(ehloCmd))
    assert(ehloCmd.getResulCode == ehloCmd.getSuccessResponseCode)
    val vrfyCmd = new Vrfy("mbianchi")
    assert(smtpClient.sendCommand(vrfyCmd))
   // assert(vrfyCmd.getResulCode == "502") //502==Command not implemented
  }
}
