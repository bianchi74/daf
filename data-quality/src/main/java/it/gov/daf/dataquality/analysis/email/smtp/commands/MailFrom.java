package it.gov.daf.dataquality.analysis.email.smtp.commands;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class MailFrom extends SmtpCommand {


    public MailFrom(String fromAddress){
        super();
        command = "MAIL FROM: <"+ fromAddress + ">";
        successResponseCode = "250";
        unsuccessResponseCode = new HashSet<>(Arrays.asList("552","451","452","500","501","421"));
    }


}
