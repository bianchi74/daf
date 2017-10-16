package it.gov.daf.dataquality.analysis.web.http;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class HttpVerifier {

    private static final Logger logger = Logger.getLogger(HttpVerifier.class);
    private static HttpVerifier verifier;

    private HttpVerifier(){}

    public static HttpVerifier getInstance(){
        if (verifier==null){
            verifier = new HttpVerifier();
        }
        return verifier;
    }

    public synchronized Map<String,String> checkHttp(String toBeChecked){

        Map<String,String> result = new HashMap<>();
        result.put("url",toBeChecked);
        WebClient wc = new WebClient();
        wc.getOptions().setRedirectEnabled(false);
        try {
            WebRequest request = new WebRequest(new URL(toBeChecked));
            WebResponse response = wc.loadWebResponse(request);
            result.put("server", "True");
            int statusCode = response.getStatusCode();

            if (statusCode==302){
                result.put("redirect", "True");
                wc.getOptions().setRedirectEnabled(true);
                response = wc.loadWebResponse(request);
                statusCode = response.getStatusCode();
                boolean isRisky = HmtlChecker.isRisky(response);
                if (isRisky) result.put("is-risky", "True"); else result.put("is-risky", "False");
            }

            result.put("http-code", Integer.toString(statusCode));
            logger.debug(response.getContentAsString());

        }catch (IOException ioe) {
            logger.debug(ioe.getMessage());
            result.put("server", "False");
        }
        finally {
            wc.close();
        }
        return result;
    }
}
