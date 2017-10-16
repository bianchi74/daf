package it.gov.daf.dataquality.analysis.email.smtp.commands;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class Vrfy extends SmtpCommand {

    public Vrfy(String user){
        super();
        this.command = "VRFY "+ user;
        this.successResponseCode = "250";
        this.unsuccessResponseCode =
                new HashSet<>(Arrays.asList("251","252","550","551","553","500","501","502","504","421"));
    }

}
