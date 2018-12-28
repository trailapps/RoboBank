package com.robobank.util;

import com.robobank.validator.CustomerStatementValidator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class CSVWriter {
    public static void writeInvalidCustomerStatements(Set<String> invalidCustomerDetails, String reportName) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(reportName);
            writer.append("Transaction reference").append(',').append("description").append('\n');

            if (Objects.nonNull(invalidCustomerDetails)) {
                for (String transactionRefDescriptionKey : invalidCustomerDetails) {
                    String[] transactionRefDescription = transactionRefDescriptionKey.split(CustomerStatementValidator.RECORD_SEPARATOR);
                    writer.append(transactionRefDescription[0]).append(',').append(transactionRefDescription[1]).append('\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }


    }
}
