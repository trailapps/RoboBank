package com.robobank;

import com.robobank.reader.CSVStatementReader;
import com.robobank.reader.XMLStatementReader;
import com.robobank.util.InvalidCustomerStatementCSVWriter;
import com.robobank.validator.CustomerStatementValidator;

import java.io.IOException;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        CSVStatementReader csvStatementReader = new CSVStatementReader();
        XMLStatementReader xmlStatementReader = new XMLStatementReader();

        Set<String> invalidCustomerStatementsInCSV = CustomerStatementValidator.getInvalidCustomerStatements(csvStatementReader.getCustomerStatements());
        InvalidCustomerStatementCSVWriter.writeInvalidCustomerStatements(invalidCustomerStatementsInCSV, "InvalidRecordsInCSVFormat.csv");

        Set<String> invalidCustomerStatementsInXML = CustomerStatementValidator.getInvalidCustomerStatements(xmlStatementReader.getCustomerStatements());
        InvalidCustomerStatementCSVWriter.writeInvalidCustomerStatements(invalidCustomerStatementsInXML, "InvalidRecordsInXMLFormat.csv");
        // Invalid customer statements in XML & CSV
        invalidCustomerStatementsInCSV.addAll(invalidCustomerStatementsInXML);
        InvalidCustomerStatementCSVWriter.writeInvalidCustomerStatements(invalidCustomerStatementsInCSV, "InvalidRecordsSummary.csv");

    }
}
