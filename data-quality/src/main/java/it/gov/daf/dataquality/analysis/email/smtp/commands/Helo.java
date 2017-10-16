package it.gov.daf.dataquality.analysis.email.smtp.commands;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class Helo extends SmtpCommand {

    public Helo(String domainFrom){
        super();
        command = "HELO " + domainFrom ;
        successResponseCode = "250";
        unsuccessResponseCode = new HashSet<>(Arrays.asList("500","501","504","521","412"));
    }
}
