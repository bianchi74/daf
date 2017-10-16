package it.gov.daf.dataquality.analysis.email.smtp.commands;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class Rcpt extends SmtpCommand {


    public Rcpt(String toAddress){
        super();
        command = "RCPT TO: <" + toAddress + ">";
        successResponseCode = "250";
        unsuccessResponseCode = new HashSet<>(
                Arrays.asList("251","550","551","552","553","450","451","452","500","501","503","521","421"));
    }

}
