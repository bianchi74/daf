package it.gov.daf.dataquality.analysis;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class EmailAnalyserTest extends TestCase {

    @Test
    public void testAnalyse() {
        EmailAnalyser emailAnalyser = new EmailAnalyser();

        //println(emailAnalyser.analyse("mbianchi@fub.it"))
        //println(emailAnalyser.analyse("mbianchi@fub.it"))
        //println(emailAnalyser.analyse("bianchi74@gmail.com"))

        //println(emailAnalyser.analyse("marco.bianchi@agid.gov.it"))
        //println(emailAnalyser.analyse("prot.gen.asl.vt.it@legalmail.it"))
        //println(emailAnalyser.analyse("aslumbria2@postacert.umbria.it"))
        System.out.println(emailAnalyser.analyse("affarigenerali.asprc@certificatamail.it"));

    }
}
