package it.gov.daf.dataquality.analysis.email.smtp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class ReplyCodeLegend {

    private ReplyCodeLegend(){}

    //TODO Move message definition into a txt file.
    private static final Map<String, String> legend = new HashMap<String, String>() {{
        put("200", "Nonstandard success response, see rfc876.");
        put("211", "System status, or system help reply");
        put("214", "Help message");
        put("220", "<domain> Service ready");
        put("221", "<domain> Service closing transmission channel");
        put("250", "Requested mail action okay, completed");
        put("251", "User not local; will forward to <forward-path>");
        put("252", "Cannot VRFY user, but will accept message and attempt delivery");
        put("354", "Start mail input; end with <CRLF>.<CRLF>");
        put("421", "<domain> Service not available, closing transmission channel");
        put("450", "Requested mail action not taken: mailbox unavailable");
        put("451", "Requested action aborted: local error in processing");
        put("452", "Requested action not taken: insufficient system storage");
        put("500", "Syntax error, command unrecognised");
        put("501", "Syntax error in parameters or arguments");
        put("502", "Command not implemented");
        put("503", "Bad sequence of commands");
        put("504", "Command parameter not implemented");
        put("521", "<domain> does not accept mail (see rfc1846)");
        put("530", "Access denied");
        put("550", "Requested action not taken: mailbox unavailable");
        put("551", "User not local; please try <forward-path>");
        put("552", "Requested mail action aborted: exceeded storage allocation");
        put("553", "Requested action not taken: mailbox name not allowed");
        put("554", "Transaction failed");
    }};

    public static String getDescription(String smtpReplyCode){

        String description = legend.get(smtpReplyCode);
        if (description==null) {
            description = "Wrong code does not produce description!";
        }
        return description;
    }
}
