package it.gov.daf.dataquality.analysis;

import it.gov.daf.dataquality.analysis.email.checkers.*;
import it.gov.daf.dataquality.analysis.email.smtp.SmtpVerifier;
import it.gov.daf.dataquality.utils.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Validates email addresses.
 *
 * @author Marco Bianchi (bianchi74@gmail.com)
 * @version 0.1
 *
 */

public class EmailAnalyser implements Analyser{

    public Map<String, String> analyse(String emailAddress){

        Map<String, String> staticCheckResult = new HashMap<>();

        staticCheckResult.put("address", emailAddress);

        String trueLabel = "True";
        String falseLabel = "False";

        // Check Syntax
        if (SyntacticalChecker.getInstance().isChecked(emailAddress)){
            staticCheckResult.put("syntax", trueLabel);
        } else {
            staticCheckResult.put("syntax", falseLabel);
            return staticCheckResult;
        }

        // Check SMTP
        Map<String, String> dynamicCheckResult = SmtpVerifier.getInstance().checkSmtp(emailAddress);

        // if SMTP check test is successful
        if (!dynamicCheckResult.get("mx-records").equalsIgnoreCase("0") &&
                dynamicCheckResult.get("smtp-summary").equalsIgnoreCase(trueLabel)){
            // Check Role
            if (RoleChecker.getInstance().isChecked(emailAddress)){
                staticCheckResult.put("role", trueLabel);
            } else {
                staticCheckResult.put("role", falseLabel);
            }

            if (ReservedChecker.getInstance().isChecked(emailAddress)){
                staticCheckResult.put("reserved", trueLabel);
            } else {
                staticCheckResult.put("reserved", falseLabel);
            }

            if (DisposableChecker.getInstance().isChecked(emailAddress)){
                staticCheckResult.put("disposable", trueLabel);
            } else {
                staticCheckResult.put("disposable", falseLabel);
            }

            if (FreeMailChecker.getInstance().isChecked(emailAddress)){
                staticCheckResult.put("free-provider", trueLabel);
            } else {
                staticCheckResult.put("free-provider", falseLabel);
            }

            if (PecChecker.getInstance().isChecked(emailAddress)){
                staticCheckResult.put("pec", trueLabel);
            } else {
                staticCheckResult.put("pec", falseLabel);
            }

        }

        return CollectionUtils.mergeMaps(staticCheckResult,dynamicCheckResult);
    }



}