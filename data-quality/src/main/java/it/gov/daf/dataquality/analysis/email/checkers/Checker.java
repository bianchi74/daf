package it.gov.daf.dataquality.analysis.email.checkers;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public interface Checker {

    boolean isChecked(String emailAddress);

}
