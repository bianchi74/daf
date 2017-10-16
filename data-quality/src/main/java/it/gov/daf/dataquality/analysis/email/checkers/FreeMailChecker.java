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
public class FreeMailChecker implements Checker {

    private static final Logger logger = Logger.getLogger(FreeMailChecker.class);

    private static FreeMailChecker singleton;

    //List by https://gist.github.com/tbrianjones/5992856
    private static final String FREE_EMAIL_PROVIDER_FILE_PATHNAME = "src/resources/free-email-providers.txt";
    private static final String FILE_ENCODING = "UTF-8";

    private Set<String> freeEmailProviders;

    private FreeMailChecker(){}

    public static Checker getInstance(){
        if (singleton == null) {
            try {
                singleton = new FreeMailChecker();
                singleton.freeEmailProviders = new HashSet<>(
                        FileUtils.readLines(new File(FREE_EMAIL_PROVIDER_FILE_PATHNAME), FILE_ENCODING));
            } catch (IOException ioe){
                logger.error(FREE_EMAIL_PROVIDER_FILE_PATHNAME + " not found!");
                return null;
            }
        }
        return singleton;
    }

    @Override
    public boolean isChecked(String emailAddress) {
        String domain = emailAddress.substring(emailAddress.indexOf('@')+1,emailAddress.length());
        return freeEmailProviders.contains(domain);
    }
}

