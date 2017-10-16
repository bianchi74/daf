package it.gov.daf.dataquality.analysis.email.smtp.commands;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */

public class Ehlo extends SmtpCommand {


    public Ehlo(String domainFrom){
        super();
        command = "EHLO " + domainFrom;
        successResponseCode = "250";
        unsuccessResponseCode = new HashSet<>(Arrays.asList("550","500","501","504","412"));
    }

}
