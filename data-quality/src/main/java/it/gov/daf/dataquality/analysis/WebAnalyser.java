package it.gov.daf.dataquality.analysis;

import it.gov.daf.dataquality.utils.CollectionUtils;
import it.gov.daf.dataquality.analysis.web.http.HttpVerifier;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class WebAnalyser implements Analyser {

    private static UrlValidator validator =
            org.apache.commons.validator.routines.UrlValidator.getInstance();

    @Override
    public Map<String, String> analyse(String url) {

        Map<String, String> staticResult = new HashMap<>();

        staticResult.put("url",url);

        // Check syntax
        boolean validSyntax = validator.isValid(url);

        if (validSyntax){

            staticResult.put("syntax","True");

            // Check HTTP
            HttpVerifier verifier= HttpVerifier.getInstance();
            Map<String, String> dynamicResult = verifier.checkHttp(url);

            return CollectionUtils.mergeMaps(staticResult,dynamicResult);


        } else {
            staticResult.put("syntax","False");
            return staticResult;
        }
    }
}
