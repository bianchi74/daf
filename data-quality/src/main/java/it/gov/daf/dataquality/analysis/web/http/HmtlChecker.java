package it.gov.daf.dataquality.analysis.web.http;

import com.gargoylesoftware.htmlunit.WebResponse;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class HmtlChecker {

    private static final Logger logger = Logger.getLogger(HmtlChecker.class);

    private static final String HEURISTIC_FILE_PATHNAME = "src/resources/heuristic-webErrorPage.txt";
    private static final String FILE_ENCODING = "UTF-8";

    private static Set<String> heuristicRules;

    private HmtlChecker(){}

    public static boolean isRisky(WebResponse response){

        try {
            if (heuristicRules==null){
                heuristicRules = new HashSet<>(
                        FileUtils.readLines(new File(HEURISTIC_FILE_PATHNAME), FILE_ENCODING));
            }

            String pageContent = response.getContentAsString();

            for(String rule: heuristicRules){
                if (pageContent.contains(rule)){
                    return true;
                }
            }
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
}
