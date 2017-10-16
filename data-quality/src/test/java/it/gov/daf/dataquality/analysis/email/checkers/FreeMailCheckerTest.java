package it.gov.daf.dataquality.analysis.email.checkers;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class FreeMailCheckerTest extends TestCase {

    @Test
    public void testIsChecked(){
        Checker checker = FreeMailChecker.getInstance();
        assert(! checker.isChecked("someone@agid.gov.it"));
        assert(checker.isChecked("someone@gmail.com"));
    }

}

