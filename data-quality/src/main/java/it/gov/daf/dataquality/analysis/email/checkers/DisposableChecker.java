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
public class DisposableChecker implements Checker{

    private static final Logger logger = Logger.getLogger(DisposableChecker.class);

    private static DisposableChecker singleton;

    //list by https://gist.github.com/michenriksen/8710649
    private static final String DISPOSABLE_FILE_PATHNAME = "src/resources/disposable.txt";
    private static final String FILE_ENCODING = "UTF-8";

    private Set<String> disposable;

    private DisposableChecker(){}

    public static Checker getInstance(){
        if (singleton == null) {
            try {
                singleton = new DisposableChecker();
                singleton.disposable = new HashSet<>(
                    FileUtils.readLines(new File(DISPOSABLE_FILE_PATHNAME), FILE_ENCODING));
            } catch (IOException ioe){
                logger.error(DISPOSABLE_FILE_PATHNAME + " not found!");
                return null;
            }
        }
        return singleton;
    }

    @Override
    public boolean isChecked(String emailAddress) {
        String domain = emailAddress.substring(emailAddress.indexOf('@')+1,emailAddress.length());
        return disposable.contains(domain);
    }
}