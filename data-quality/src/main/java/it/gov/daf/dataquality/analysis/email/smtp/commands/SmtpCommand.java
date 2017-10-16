package it.gov.daf.dataquality.analysis.email.smtp.commands;

import org.apache.log4j.Logger;

import java.util.Set;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public abstract class SmtpCommand {

    protected static final Logger logger = Logger.getLogger(SmtpCommand.class);

    protected String command;

    protected String successResponseCode;
    protected Set<String> unsuccessResponseCode;

    protected String resultCode = "Command not yet sent!";

    public void setResulCode(String code){
        resultCode = code;
    }

    public String getResulCode(){
        return resultCode;
    }

    public String getSuccessResponseCode(){
        return successResponseCode;
    }

    public boolean successfulReply(String resultCode){
        if (resultCode.equalsIgnoreCase(this.successResponseCode)) {
            return true;
        } else if (unsuccessResponseCode.contains(resultCode)){
            return false;
        } else {
            throw new SmtpCommandException("Unexpected code for the command " + getCommand());
        }
    }

    public String getCommand() {
        return command;
    }

}
