package it.gov.daf.dataquality.discovery.web.subdomains.strategies;

import java.util.Set;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public abstract class DiscoveryStrategy {

    private String strategyName;

    public String getStrategyName(){
        return strategyName;
    }

    void setStrategyName(String strategyName){
        this.strategyName=strategyName;
    }

    public abstract Set<SubdomainResult> findSubdomains(String domain);

}
