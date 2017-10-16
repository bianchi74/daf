package it.gov.daf.dataquality.analysis.email.smtp;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This class has been implemented on the basis of the example available at:
 * http://forum.vingrad.ru/act-Print/client/printer/f-108/t-83347.html
 *
 * @author Marco Bianchi (bianchi74@gmail.com)
 *
 */

public class SmtpServerResolver {

    private static SmtpServerResolver resolver;
    private SmtpServerResolver(){}

    public static SmtpServerResolver getInstance(){
        if (resolver==null) {
            resolver = new SmtpServerResolver();
        }
        return resolver;
    }

    public String[] mailServerLookup(String address) {

        String domain = address.substring(address.indexOf('@') + 1, address.length());

        try {
            InitialDirContext initialDirContext = new InitialDirContext();
            Attributes attributes = initialDirContext.getAttributes("dns:/" + domain, new String[]{"MX"});
            Attribute attributeMX = attributes.get("MX");
            // according to RFC 974 if there are no MX RRs then default to domainName
            if (attributeMX == null) {
                return (new String[]{domain});
            } else {
                // split MX RRs into Preference Values(pvhn[0]) and Host Names(pvhn[1])
                String[][] pvhn = new String[attributeMX.size()][2];
                for (int i = 0; i < attributeMX.size(); i++) {
                    pvhn[i] = ("" + attributeMX.get(i)).split("\\s+");
                }
                // sort the MX RRs by RR value (lower is preferred)
                Arrays.sort(pvhn, new Comparator<String[]>() {
                    public int compare(String[] o1, String[] o2) {
                        return (Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]));
                    }
                });
                // put sorted host names in an array, get rid of any trailing '.'
                String[] sortedHostNames = new String[pvhn.length];
                for (int i = 0; i < pvhn.length; i++) {
                    sortedHostNames[i] = pvhn[i][1].endsWith(".") ?
                            pvhn[i][1].substring(0, pvhn[i][1].length() - 1) : pvhn[i][1];
                }
                return sortedHostNames;
            }
        } catch (NamingException e) {
            return new String[0];
        }
    }


}
