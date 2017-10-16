package it.gov.daf.dataquality.analysis.email.checkers;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class SyntacticalChecker implements Checker {

    private static SyntacticalChecker singleton;

    private static EmailValidator apacheValidator =
            org.apache.commons.validator.routines.EmailValidator.getInstance();

    private SyntacticalChecker(){}

    public static Checker getInstance() {
        if (singleton == null) {
            singleton = new SyntacticalChecker();
        }
        return singleton;
    }

    @Override
    public boolean isChecked(String emailAddress) {
        return apacheValidator.isValid(emailAddress);
    }
}
