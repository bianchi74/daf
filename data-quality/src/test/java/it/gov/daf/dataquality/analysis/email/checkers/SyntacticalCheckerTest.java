package it.gov.daf.dataquality.analysis.email.checkers;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class SyntacticalCheckerTest extends TestCase {

    private Checker checker = SyntacticalChecker.getInstance();

  /*
   * SYNTACTICAL CHECKS see RFC2822
   * http://codefool.tumblr.com/post/15288874550/list-of-valid-and-invalid-email-addresses
   */

    @Test
    // TRUE-Positive
    public void testIsChecked_validEmailAddresses(){
        assert(checker.isChecked("email@example.com"));
        assert(checker.isChecked("firstname.lastname@example.com"));
        assert(checker.isChecked("email@subdomain.example.com"));
        assert(checker.isChecked("firstname+lastname@example.com"));
        //assert(checker.isChecked("email@123.123.123.123")) ???
        assert(checker.isChecked("email@[123.123.123.123]"));
        assert(checker.isChecked("\"email\"@example.com"));
        assert(checker.isChecked("1234567890@example.com"));
        assert(checker.isChecked("email@example-one.com"));
        assert(checker.isChecked("_______@example.com"));
        assert(checker.isChecked("email@example.name"));
        assert(checker.isChecked("email@example.museum"));
        assert(checker.isChecked("email@example.co.jp"));
        assert(checker.isChecked("firstname-lastname@example.com"));
        assert(checker.isChecked("あいうえお@example.com"));
        assert(checker.isChecked("much.\"more\\ unusual\"@example.com"));
        assert(checker.isChecked("very.unusual.\"@\".unusual.com@example.com"));
    }

    @Test
    // TRUE-NEGATIVE
    public void testIsChecked_invalidEmailAddresses(){
        assert(! checker.isChecked("plainaddress"));
        assert(! checker.isChecked("#@%^%#$@#$@#.com"));
        assert(! checker.isChecked("@example.com"));
        assert(! checker.isChecked("Joe Smith <email@example.com>"));
        assert(! checker.isChecked("email.example.com"));
        assert(! checker.isChecked("email@example@example.com"));
        assert(! checker.isChecked(".email@example.com"));
        assert(! checker.isChecked("email.@example.com"));
        assert(! checker.isChecked("email..email@example.com"));
        assert(! checker.isChecked("email@example.com (Joe Smith)"));
        assert(! checker.isChecked("email@example"));
        assert(! checker.isChecked("email@-example.com"));
        assert(! checker.isChecked("email@example.web"));
        assert(! checker.isChecked("email@111.222.333.44444"));
        assert(! checker.isChecked("email@example..com"));
        assert(! checker.isChecked("Abc..123@example.com"));
        assert(! checker.isChecked("me@"));
        assert(! checker.isChecked("me.@example.com"));
        assert(! checker.isChecked("me@example..com"));
        assert(! checker.isChecked("me.example@com"));
        assert(! checker.isChecked("me\\@example.com"));
        assert(! checker.isChecked("\"(),:;<>[\\]@example.com"));
        assert(! checker.isChecked("just\"not\"right@example.com"));
        assert(! checker.isChecked("this\\ is\"really\"not\\allowed@example.com"));
    }
}
