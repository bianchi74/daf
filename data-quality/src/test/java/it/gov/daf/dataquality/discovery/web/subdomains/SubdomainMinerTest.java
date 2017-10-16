package it.gov.daf.dataquality.discovery.web.subdomains;

import it.gov.daf.dataquality.discovery.web.subdomains.strategies.SubdomainResult;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Set;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class SubdomainMinerTest extends TestCase {

    @Test
    public void testDiscoverSubdomains(){
        Set<SubdomainResult> subdomains = SubdomainsMiner.discoverSubdomains(("salute.gov.it"));
        System.out.println(subdomains);
    }

}
