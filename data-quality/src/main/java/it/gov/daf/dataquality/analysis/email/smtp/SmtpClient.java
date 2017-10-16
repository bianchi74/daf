package it.gov.daf.dataquality.analysis.email.smtp;

import it.gov.daf.dataquality.analysis.email.smtp.commands.SmtpCommand;
import it.gov.daf.dataquality.analysis.email.smtp.commands.Quit;
import it.gov.daf.dataquality.analysis.email.smtp.commands.SmtpCommandException;
import it.gov.daf.dataquality.utils.Configurator;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class SmtpClient{

    private static Properties properties = Configurator.loadProps("src/resources/smtp-client.properties");
    private static final Logger logger = Logger.getLogger(SmtpClient.class);

    private int SMTP_PORT = 25;

    // Replace the following address using the
    private String mailFrom;
    private String heloDomain;

    private Socket smtpPipe;
    private PrintWriter toServer;
    private BufferedReader fromServer;
    private String errorCode;

    private boolean isConnected = false;

    public SmtpClient(){

        this.mailFrom = properties.getProperty("mailFrom.sender");
        this.heloDomain = properties.getProperty("helo.domainFrom");

    }

    public String getHeloDomain(){
        return heloDomain;
    }

    public String getMailFrom(){
        return mailFrom;
    }

    public String getErrorCode(){
        return errorCode;
    }

    public boolean connect(String host){

        InputStream inputStream;
        OutputStream outputStream;

        try {
            InetAddress mailHost = InetAddress.getByName(host);

            smtpPipe = new Socket(mailHost, SMTP_PORT);
            inputStream = smtpPipe.getInputStream();
            outputStream = smtpPipe.getOutputStream();
            isConnected = true;
            errorCode = "-1";
        } catch (IOException ioe) {
            return false;
        }

        try{
            toServer = new PrintWriter(new OutputStreamWriter(outputStream), true);
            fromServer = new BufferedReader(new InputStreamReader(inputStream));

            String initialID = fromServer.readLine();
            logger.debug(initialID);

            if (!initialID.startsWith("220")){
                errorCode = initialID.substring(0,3);
              return false;
            } 

        } catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean disconnect (){

        if (! sendCommand(new Quit())) return false;

        try {
            fromServer.close();
            toServer.close();
            smtpPipe.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;

    }

    boolean sendCommand(SmtpCommand command){

        if (!isConnected()) throw new SmtpCommandException("You have to connect to the server before send commands!");

        try {
            logger.debug("Sending command: " + command.getCommand());
            toServer.println(command.getCommand());
            logger.debug("Waiting for server answer... ");
            String result = fromServer.readLine();
            logger.debug("Answer received: " + result);
            String resultcode = result.substring(0, 3);
            command.setResulCode(resultcode);
            if (command.successfulReply(resultcode)){
                return true;
            } else {
                errorCode = resultcode;
            }
        } catch (IOException e){
           logger.error(e.getMessage());
        }
        return false;
    }

    public boolean isConnected(){
        return isConnected;
    }
}