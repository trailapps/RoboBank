package com.robobank.validator;

import com.robobank.model.CustomerStatement;

import java.util.*;

/**
 * Class created to validate customer reports in terms duplicate transaction reference and errors in end balance after a transaction
 */

public class CustomerStatementValidator {
    public static final String RECORD_SEPARATOR = "@#";

    /**
     * @param customerStatements : List of customer statement
     * @return : Map of customer statements includes transaction reference and description
     */
    public static Set<String> getInvalidCustomerStatements(List<CustomerStatement> customerStatements) {
        Map<Integer, String> customerStatementMap = new HashMap<>();
        Set<String> invalidCustomerStatementSet = new HashSet<>();
        for (CustomerStatement customerStatement : customerStatements) {

            int transactionReference = customerStatement.getTransactionReference();
            String description = customerStatement.getDescription();


            if (customerStatementMap.get(transactionReference) == null) {
                customerStatementMap.put(transactionReference, description);
            } else {
                // Add the duplicate records

                invalidCustomerStatementSet.add(String.valueOf(new StringBuilder().append(transactionReference).append(RECORD_SEPARATOR).append(description)));
                // Add parent duplicate records as well
                invalidCustomerStatementSet.add(String.valueOf(new StringBuilder().append(transactionReference).append(RECORD_SEPARATOR).append(customerStatementMap.get(transactionReference))));
            }


            if (customerStatement.getEndBalance().compareTo(customerStatement.getStartBalance().add(customerStatement.getMutation())) != 0) {
           /*     System.out.println("Expected " + customerStatement.getEndBalance() +
                        " found" + customerStatement.getStartBalance().add(customerStatement.getMutation()));
           */
                invalidCustomerStatementSet.add(String.valueOf(new StringBuilder().append(transactionReference).append(RECORD_SEPARATOR).append(description)));

            }

        }
        return invalidCustomerStatementSet;
    }
}
