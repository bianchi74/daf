package it.gov.daf.dataquality.analysis.email.smtp;

import it.gov.daf.dataquality.analysis.email.smtp.commands.*;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class SmtpClientTest extends TestCase {

    private SmtpClient smtpClient = new SmtpClient();

    @Before
    protected void setUp()  {
        assert(smtpClient.connect("mail.fub.it"));
    }

    @After
    protected void tearDown() {
        assert(smtpClient.disconnect());
    }

    @Test
    public void testEhloCmdTest(){
        Ehlo ehloCmd = new Ehlo("mail.fub.it");
        assert(smtpClient.sendCommand(ehloCmd));
        assert(ehloCmd.getResulCode() == ehloCmd.getSuccessResponseCode());
    }

    @Test
    public void testHeloCmdTest(){
        Helo heloCmd = new Helo("mail.fub.it");
        assert(smtpClient.sendCommand(heloCmd ));
        assert(heloCmd.getResulCode() == heloCmd.getSuccessResponseCode());

    }

    @Test
    public void testMailFromTest(){
        Ehlo ehloCmd = new Ehlo("mail.fub.it");
        assert(smtpClient.sendCommand(ehloCmd));
        MailFrom mailFrom = new MailFrom("mbianchi@fub.it");
        assert(smtpClient.sendCommand(mailFrom));
        assert(mailFrom.getResulCode() == mailFrom.getSuccessResponseCode());
    }

    @Test
    public void testMailToTest(){
        Ehlo ehloCmd = new Ehlo("mail.fub.it");
        assert(smtpClient.sendCommand(ehloCmd));
        MailFrom mailFrom = new MailFrom("mbianchi@fub.it");
        assert(smtpClient.sendCommand(mailFrom));
        assert(mailFrom.getResulCode() == mailFrom.getSuccessResponseCode());
        Rcpt mailTo = new Rcpt("mbianchi@fub.it");
        assert(smtpClient.sendCommand(mailTo));
        assert(mailTo.getResulCode() == mailTo.getSuccessResponseCode());
    }

    @Test
    public void testVrfyTest(){
        Ehlo ehloCmd = new Ehlo("mail.fub.it");
        assert(smtpClient.sendCommand(ehloCmd));
        assert(ehloCmd.getResulCode() == ehloCmd.getSuccessResponseCode());
        Vrfy vrfyCmd = new Vrfy("mbianchi");
        assert(smtpClient.sendCommand(vrfyCmd));
        // assert(vrfyCmd.getResulCode() == "502") //502==Command not implemented

    }
}