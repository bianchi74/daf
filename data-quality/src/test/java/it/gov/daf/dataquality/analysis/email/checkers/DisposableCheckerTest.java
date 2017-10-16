package it.gov.daf.dataquality.analysis.email.checkers;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class DisposableCheckerTest extends TestCase {

    @Test
    public void testIsChecked(){
        Checker checker = DisposableChecker.getInstance();
        assert(checker.isChecked("username@despam.it"));
        assert(! checker.isChecked("username@notDisposable.it"));
    }
}
