package it.gov.daf.dataquality.analysis;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Map;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class WebAnalyserTest extends TestCase {

    private WebAnalyser analyser = new WebAnalyser();

    @Test
    public void testAnalyseValidURLs() {

        // Syntactically correct url. Not existing server
        Map test_valid_01 = analyser.analyse("http://www.teamdigitale.gov.it");
        assert(test_valid_01 .get("url") == "http://www.teamdigitale.gov.it");
        assert(test_valid_01 .get("syntax") == "True");
        assert(test_valid_01 .get("server") == "False");
        System.out.println(test_valid_01);

    }

    @Test
    public void testAnalyseInvalidURLs(){

        // Syntactically incorrect url
        Map test_invalid_01 = analyser.analyse("www.teamdigitale.gov.it");
        assert(test_invalid_01.get("url") == "www.teamdigitale.gov.it");
        assert(test_invalid_01.get("syntax") == "False");
        System.out.println(test_invalid_01);

    }
}
