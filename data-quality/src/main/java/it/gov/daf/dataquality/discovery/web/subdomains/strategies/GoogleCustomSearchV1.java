package it.gov.daf.dataquality.discovery.web.subdomains.strategies;

import it.gov.daf.dataquality.utils.Configurator;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 *
 * Uses a Google Custom Search (https://developers.google.com/custom-search/docs/js/cselement-devguide)
 * to discover the subdomains of a given domain. </br>
 * For example, to discover the subdomain of the domain governo.it the class submit the query: </br>
 * </br>
 * "site:governo.it governo.it"
 * </br>
 * Google returns results form sites like: </br>
 * www.italiasicura.governo.it </br>
 * www.teamdigitale.governo.it </br>
 * and so on... </br>
 * </br>
 *
 * Note: Google custom search v1 will stop working on December 4, 2017. </br>
 * See: https://productforums.google.com/forum/#!topic/customsearch/4J_hDR7pIf8 </br>
 *
 * @author Marco Bianchi (bianchi74@gmail.com)
 * @deprecated Use instead it.gov.daf.dataquality.miners.web.subdomains.strategies.GoogleCustomSearchV2
 *
 */

public class GoogleCustomSearchV1 extends DiscoveryStrategy {

    private static GoogleCustomSearchV1 googleCustomSearchV1;

    private static final Logger logger = Logger.getLogger(GoogleCustomSearchV1.class);


    private int maxNumberOfRequestsPerDomain;
    private String googleKey;
    private String googleHost;
    private String cseId;

    private GoogleCustomSearchV1(){}

    private GoogleCustomSearchV1(String configurationFile){

            Properties properties = Configurator.loadProps(configurationFile);
            maxNumberOfRequestsPerDomain =
                    Integer.parseInt(properties.getProperty("maxNumberOfRequestsPerDomain","10"));
            if (((googleKey = properties.getProperty("googleKey"))==null) ||
                    ((googleHost = properties.getProperty("googleHost"))==null) ||
                    ((cseId = properties.getProperty("cseId"))==null))
                new RuntimeException("GoogleCustomSearchV1 not properly configured.\n" +
                "Please check properties in " + configurationFile);
    }


    /**
     * Implements the singleton design pattern since Google provides a limitated number of
     * free-of-charge queries. JSON/Atom Custom Search API provides 100 search queries per day for free.
     * If you need more, you may sign up for billing in the API Console.
     * Additional requests cost $5 per 1000 queries, up to 10k queries per day.
     *
     * @param configurationFile
     * @return
     */
    public static GoogleCustomSearchV1 getInstance(String configurationFile){
        if (googleCustomSearchV1==null){
            googleCustomSearchV1 = new GoogleCustomSearchV1(configurationFile);
        }
        return googleCustomSearchV1;
    }

    @Override
    public Set<SubdomainResult> findSubdomains(String domain) {
        return buildResultSet(removeDuplicates(domain, executeQuery(domain)));
    }

    /*
     * Performs a custom search query to GoogleCustomSearchV1.
     */
    private Set<JSONObject> executeQuery(String domain) {

        Set<JSONObject> jsonObjectResults = new HashSet<>();

        String result = null;

        String startIndexParam = "";
            int startIndex = 1;

            for (int count = 0; count < maxNumberOfRequestsPerDomain; count++) {
                try {
                    result = httpGet("https://www.googleapis.com/customsearch/v1?"
                            + "key=" + googleKey + "&cx=" + cseId + "&"
                            + startIndexParam + "googlehost=" + googleHost
                            + "&siteSearch=" + domain + "&q=" + domain);

                    JSONObject jsonObj = new JSONObject(result);
                    JSONArray jsonArray = jsonObj.getJSONArray("items");
                    JSONObject resultItem;

                    for (int i = 0; i < jsonArray.length(); i++) {

                        resultItem = jsonArray.getJSONObject(i);
                        jsonObjectResults.add(resultItem);

                    }

                    JSONObject queries = (JSONObject) jsonObj.get("queries");

                    JSONArray nextPage = queries.getJSONArray("nextPage");

                    JSONObject nextPageObj = (JSONObject) nextPage.get(0);
                    startIndex = nextPageObj.getInt("startIndex");
                    startIndexParam = "start=" + startIndex + "&";

                } catch (JSONException jsonException) {
                    logger.debug("It is not required to exec " + maxNumberOfRequestsPerDomain
                            + " google request to discover subdomains related to " + domain);
                    break;
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        return jsonObjectResults;
    }

    /*
     * Simply performes a http Get
     * @todo refactor this method
     */
    private String httpGet(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }

        BufferedReader rd = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb.toString();
    }

    private Set<SubdomainResult> buildResultSet(Set<JSONObject> results) {

        Set<SubdomainResult> result = new HashSet<>();

        try {
            Iterator<JSONObject> iterator = results.iterator();
            while (iterator.hasNext()) {

                JSONObject jsonResult = iterator.next();

                result.add(new SubdomainResult(
                        jsonResult.getString("displayLink"),
                        jsonResult.getString("link"),
                        jsonResult.getString("title"),
                        jsonResult.getString("snippet"))
                );
            }
        } catch (JSONException jsonEx) {
            logger.error(jsonEx.getMessage());
        }
        return result;
    }

    private Set<JSONObject> removeDuplicates(String domain, Set<JSONObject> input) {

        Set<String> resultList = new HashSet();
        Set<JSONObject> result = new HashSet();

        Iterator iterator = input.iterator();
        while (iterator.hasNext()) {
            JSONObject item = (JSONObject) iterator.next();
            try {
                String displayLinkValue = (String) item.get("displayLink");
                if (!resultList.contains(displayLinkValue) && displayLinkValue.endsWith(domain)) {
                        resultList.add(displayLinkValue);
                        result.add(item);
                }
            } catch (JSONException e) {
                logger.error(e.getMessage());
            }
        }
        return result;
    }
}
