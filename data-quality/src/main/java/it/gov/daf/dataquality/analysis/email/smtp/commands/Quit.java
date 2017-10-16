package it.gov.daf.dataquality.analysis.email.smtp.commands;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class Quit extends SmtpCommand {

    public Quit(){
        command = "QUIT";
        successResponseCode = "221";
        unsuccessResponseCode = new HashSet<>(Arrays.asList("500"));
    }

}
