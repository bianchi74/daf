package it.gov.daf.dataquality.analysis.email.smtp;

import it.gov.daf.dataquality.analysis.email.smtp.commands.Helo;
import it.gov.daf.dataquality.analysis.email.smtp.commands.MailFrom;
import it.gov.daf.dataquality.analysis.email.smtp.commands.Rcpt;
import it.gov.daf.dataquality.analysis.email.smtp.commands.Vrfy;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class SmtpVerifier {

    protected static final Logger logger = Logger.getLogger(SmtpVerifier.class);

    private static SmtpVerifier smptVerifier;

    private SmtpVerifier(){}

    public static SmtpVerifier getInstance(){
        if (smptVerifier==null) {
            smptVerifier = new SmtpVerifier();
        }
        return smptVerifier;
    }

    public synchronized  Map<String,String> checkSmtp(String emailAddress){

        Map<String,String> result = new HashMap<>();

        SmtpServerResolver resolver = SmtpServerResolver.getInstance();

        String[] mailServers = resolver.mailServerLookup(emailAddress);

        logger.debug("Looking for MX records...");
        // if no MX server exists the mailServerLookup method return just the domain name.
        if (mailServers.length==1 && mailServers[0].equalsIgnoreCase(
                emailAddress.substring(emailAddress.lastIndexOf('@'), emailAddress.length()))){
            result.put("mx-records", "0");
            return result;
        } else {
            result.put("mx-records", Integer.toString(mailServers.length));
        }

        SmtpClient client;
        int numberOfServers = -1;

        result.put("smtp-check-timestamp", Long.toString(System.currentTimeMillis()));

        for (String server: mailServers){
            numberOfServers++;
            String serverId = "server_" + Integer.toString(numberOfServers);
            logger.debug("Checking " + server + " server...");

            result.put(serverId, server);
            client = new SmtpClient();
            if (!client.connect(server)) {
                //@TODO Refactor verifier in order to distinguish failed socket connection and unexpected server starting message
                result.put(serverId + "-welcome", "False");
                result.put("error-code",client.getErrorCode());
                break;
            }
            if (! client.sendCommand(new Helo(client.getHeloDomain()))){
                result.put(serverId + "-helo", "False");
                result.put("error-code",client.getErrorCode());
                client.disconnect();
                break;
            }

            if (client.sendCommand(new Vrfy(emailAddress))){
                result.put(serverId+ "-vrf", "True");
                client.disconnect();
                break;
            }

            if (! client.sendCommand(new MailFrom(client.getMailFrom()))){
                result.put(serverId+ "-from", "False");
                result.put("error-code",client.getErrorCode());
                client.disconnect();
                break;
            }

            if (! client.sendCommand(new Rcpt(emailAddress))){
                result.put(serverId+ "-rcpt", "False");
                result.put("error-code",client.getErrorCode());
                client.disconnect();
                break;
            } else {
                result.put(serverId+ "-rcpt", "True");
            }

            if (client.sendCommand(new Rcpt("not.existing.user"+ emailAddress.substring(emailAddress.lastIndexOf('@'), emailAddress.length())))) {
                result.put(serverId +"-acceptAll", "True");
            } else {
                result.put(serverId +"-acceptAll", "False");
            }


            if (result.get("smtp-summary")== null) {
                result.put("smtp-summary", "True");
            }
            client.disconnect();
        }

        if (result.get("smtp-summary")== null) {
            result.put("smtp-summary", "False");
        }

        return result;
    }
}
