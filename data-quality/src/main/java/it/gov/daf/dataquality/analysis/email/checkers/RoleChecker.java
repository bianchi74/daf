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
public class RoleChecker implements Checker{

    private static final Logger logger = Logger.getLogger(RoleChecker.class);

    private static RoleChecker singleton;

    // list by https://pardot.desk.com/customer/en/portal/articles/2287046-role-based-email-address-overview
    private static final String EMAIL_ROLES_FILE_PATHNAME = "src/resources/email-roles.txt";
    private static final String FILE_ENCODING = "UTF-8";

    private Set<String> roles;

    private RoleChecker(){}

    public static Checker getInstance(){
        if (singleton == null) {
            try {
                singleton = new RoleChecker();
                singleton.roles = new HashSet<>(
                        FileUtils.readLines(new File(EMAIL_ROLES_FILE_PATHNAME), FILE_ENCODING));
            }catch (IOException ioe){
                logger.error(EMAIL_ROLES_FILE_PATHNAME + " not found!");
                return null;
            }
        }
        return singleton;
    }

    public boolean isChecked(String emailAddress){
       String prefix = emailAddress.substring(0,emailAddress.indexOf('@')+1);
        return roles.contains(prefix);
    }

}
