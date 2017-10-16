package it.gov.daf.dataquality.analysis.web.http;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class HttpVerifierTest extends TestCase {

    @Test
    public void testCorrectURLs(){

        HttpVerifier verifier = HttpVerifier.getInstance();
        assert(verifier.checkHttp("http://www.google.it").get("http-code")=="200");
        assert(verifier.checkHttp("https://twitter.com/capritourism").get("http-code")=="200");

    }

    @Test
    public void testIncorrectURLs(){

        HttpVerifier verifier = HttpVerifier.getInstance();
        assert(verifier.checkHttp("http://www.unreacheableHost.it").get("server")=="False");
        assert(verifier.checkHttp("http://www.ssalvatoreinrete.it/pagina.aspx?IDPagina=3502").get("http-code")=="403");
        assert(verifier.checkHttp("http://digilander.libero.it/asilo_di_lozzo/").get("http-code")=="200");
        assert(verifier.checkHttp("http://digilander.libero.it/asilo_di_lozzo/").get("redirect")=="True");
        assert(verifier.checkHttp("http://digilander.libero.it/asilo_di_lozzo/").get("is-risky")=="True");
    }

}
