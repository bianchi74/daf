package it.gov.daf.dataquality.discovery.web.subdomains;

import it.gov.daf.dataquality.discovery.web.subdomains.strategies.DiscoveryStrategy;
import it.gov.daf.dataquality.discovery.web.subdomains.strategies.GoogleCustomSearchV1;
import it.gov.daf.dataquality.discovery.web.subdomains.strategies.SubdomainResult;

import java.util.Set;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */

    //Domain:	agid.gov.it
    //    DNS zone transfer:	On
    //    DNS enumeration:	On
    //    Bing search:	Off
    //    GoogleCustomSearchV1 search:	Off
    //    HTML links search :	Off
    //    SSL search:	Off
    //    Smart DNS search:	Off
    //    IP information:	False

public class SubdomainsMiner {

    private SubdomainsMiner(){}

    public static Set<SubdomainResult> discoverSubdomains(String domain){

        DiscoveryStrategy strategy = GoogleCustomSearchV1.getInstance("src/resources/google.properties");
        return strategy.findSubdomains(domain);
    }

}