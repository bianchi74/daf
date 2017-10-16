package it.gov.daf.dataquality.analysis.email.checkers;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class ReservedChecker implements Checker{

    private static final Logger logger = Logger.getLogger(ReservedChecker.class);

    private static ReservedChecker singleton;

    //list by https://kb.mailchimp.com/lists/growth/limits-on-role-based-addresses
    //This list is just an example it should be customised on the basis of roles defined in the italian PA
    private static final String DISPOSABLE_FILE_PATHNAME = "src/resources/reserved.txt";
    private static final String FILE_ENCODING = "UTF-8";

    Set<String> reserved;

    private ReservedChecker(){}

    public static Checker getInstance() {
        if (singleton == null) {
            try {
                singleton = new ReservedChecker();
                singleton.reserved = new HashSet<>(
                        FileUtils.readLines(new File(DISPOSABLE_FILE_PATHNAME), FILE_ENCODING));
            }catch (IOException ioe){
                logger.error(DISPOSABLE_FILE_PATHNAME + " not found!");
                return null;
            }
        }
        return singleton;
    }

    @Override
    public boolean isChecked(String emailAddress) {
        String prefix = emailAddress.substring(0,emailAddress.indexOf('@')+1);
        return reserved.contains(prefix);
    }
}