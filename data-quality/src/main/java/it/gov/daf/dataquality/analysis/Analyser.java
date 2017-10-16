package it.gov.daf.dataquality.analysis;

import java.util.Map;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public interface Analyser {

    Map<String, String> analyse(String itemToBeAnalysed);

}
