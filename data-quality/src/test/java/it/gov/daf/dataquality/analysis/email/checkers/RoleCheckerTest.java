package it.gov.daf.dataquality.analysis.email.checkers;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class RoleCheckerTest extends TestCase {

    @Test
    public void testIsChecked(){
        Checker checker = RoleChecker.getInstance();
        assert(checker.isChecked("info@"));
        assert(!checker.isChecked("notARole@"));
    }
}
