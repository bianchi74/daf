package it.gov.daf.dataquality.discovery.web.subdomains.strategies;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class SubdomainResult {

    private String domain;
    private String link;
    private String title;
    private String snippet;

    public String getDomain() {
        return domain;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }


    public SubdomainResult(String domain, String link, String description, String snippet) {
        this.domain = domain;
        this.link = link;
        this.title = description;
        this.snippet = snippet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubdomainResult that = (SubdomainResult) o;

        return domain != null ? domain.equals(that.domain) : that.domain == null;
    }

    @Override
    public int hashCode() {
        return domain != null ? domain.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SubdomainResult{" +
                "domain='" + domain + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", snippet='" + snippet + '\'' +
                '}';
    }
}
