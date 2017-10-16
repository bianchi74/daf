package it.gov.daf.dataquality.analysis.email.checkers;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class ReservedCheckerTest extends TestCase {

    @Test
    public void testIsChecked(){
        Checker checker = ReservedChecker.getInstance();
        assert(checker.isChecked("webmaster@"));
        assert(! checker.isChecked("notReserved@"));
    }
}
