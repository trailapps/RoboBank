package com.robobank.util;

import com.robobank.validator.CustomerStatementValidator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class created to write invalid customer statements into CSV
 */
public class InvalidCustomerStatementCSVWriter {
    private static Logger logger = Logger.getLogger(InvalidCustomerStatementCSVWriter.class.getName());

    private InvalidCustomerStatementCSVWriter() {
    }

    public static void writeInvalidCustomerStatements(Set<String> invalidCustomerDetails, String reportName) throws IOException {
        try (FileWriter writer = new FileWriter(reportName)) {

            writer.append("Transaction reference").append(',').append("description").append('\n');

            if (Objects.nonNull(invalidCustomerDetails)) {
                for (String transactionRefDescriptionKey : invalidCustomerDetails) {
                    String[] transactionRefDescription = transactionRefDescriptionKey.split(CustomerStatementValidator.RECORD_SEPARATOR);
                    writer.append(transactionRefDescription[0]).append(',').append(transactionRefDescription[1]).append('\n');
                }
            }
        } catch (
                IOException e) {
            logger.log(Level.SEVERE, "Exception occurred while writing invalid customer statements into CSV " + e.getMessage());
        }
    }
}
