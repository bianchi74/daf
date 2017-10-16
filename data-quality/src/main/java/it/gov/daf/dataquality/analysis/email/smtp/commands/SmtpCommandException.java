package it.gov.daf.dataquality.analysis.email.smtp.commands;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class SmtpCommandException extends RuntimeException{

    public SmtpCommandException(String message){
        super(message);
    }

}
