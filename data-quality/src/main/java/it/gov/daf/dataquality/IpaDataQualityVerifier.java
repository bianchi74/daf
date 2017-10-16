package it.gov.daf.dataquality;

import it.gov.daf.dataquality.analysis.EmailAnalyser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class IpaDataQualityVerifier {

    public static void main(String[] args) throws Exception{

        EmailAnalyser analyser = new EmailAnalyser();

        BufferedReader br = new BufferedReader(new FileReader("src/resources/ipaEmail.txt"));
        PrintWriter pw = new PrintWriter(new File("output/ipaEmailCheck.csv"));
        String line;

        Map<String,String> report;

        pw.println("EmailAddress,syntax,numOfMxRecords,checkTimestamp,smtpSummary,errorID,pec,role,reserved,disposable,free-provider");

        while((line=br.readLine())!=null){

            line = line.trim().toLowerCase();
            report = analyser.analyse(line);
            pw.print(report.get("address") + ",");
            pw.print(report.get("syntax") + ",");
            pw.print(report.get("mx-records") + ",");
            pw.print(report.get("smtp-check-timestamp") + ",");
            pw.print(report.get("smtp-summary" + ","));
            // Aggiungere codice di errore
            if (report.get("error-code")!= null) {
                pw.print(report.get("error-code")+ ",");
            } else {pw.print(",");}

            if (report.get("pec")!= null) {
                pw.print(report.get("pec")+ ",");
            } else {pw.print(",");}

            if (report.get("role")!= null) {
                pw.print(report.get("role")+ ",");
            } else {pw.print(",");}

            if (report.get("reserved")!= null) {
                pw.print(report.get("reserved")+ ",");
            } else {pw.print(",");}

            if (report.get("disposable")!= null) {
                pw.print(report.get("disposable")+ ",");
            } else {pw.print(",");}

            if (report.get("free-provider")!= null) {
                pw.print(report.get("free-provider"));
            }

            pw.print("\n");
            pw.flush();

            TimeUnit.SECONDS.sleep(2);
        }


        br.close();
        pw.close();
    }

}
