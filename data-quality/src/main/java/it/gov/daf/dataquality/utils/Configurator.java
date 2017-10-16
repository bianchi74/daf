package it.gov.daf.dataquality.utils;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Carica un file di proprieta' di default o uno specificato per parametro.
 *
 * @author Marco Bianchi (mbianchi@fub.it)
 */
public class Configurator {

    protected static final Logger logger = Logger.getLogger(Configurator.class);

    private Configurator(){}

    /**
     * Carica un file di proprieta' specificato per parametro.
     *
     * @param propsPath path del file properties da caricare
     * @return oggetto Properties
     */
    public static Properties loadProps(String propsPath){

        Properties prop = new Properties();

        try  (FileInputStream fio = new FileInputStream(propsPath)){
            prop.load(new InputStreamReader(fio,Charset.forName("UTF-8")));
            fio.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            logger.error("Current directory: " + System.getProperty("user.dir"));
            System.exit(-1);
        }
        return prop;
    }
}