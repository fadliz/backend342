package com.xa.backend342.utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReferenceUtil {

    public static String generateReference(String lastReference) {
        // Get current year and month in YYMM format
        LocalDateTime now = LocalDateTime.now();
        String yearMonth = now.format(DateTimeFormatter.ofPattern("yyMM"));

        // Start increment at 1, or increment the last found reference
        int increment = 1;
        if (lastReference != null && lastReference.length() == 13) {
            String lastIncrementStr = lastReference.substring(9); // Extract the last 4 digits
            increment = Integer.parseInt(lastIncrementStr) + 1;
        }

        // Format increment as a 4-digit number
        String incrementStr = String.format("%04d", increment);

        // Construct new reference
        return "SLS-" + yearMonth + "-" + incrementStr;
    }
}
