package com.robobank;

import com.robobank.reader.CSVStatementReader;
import com.robobank.reader.XMLStatementReader;
import com.robobank.util.CSVWriter;
import com.robobank.validator.CustomerStatementValidator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CSVStatementReader csvStatementReader = new CSVStatementReader();
        XMLStatementReader xmlStatementReader = new XMLStatementReader();

        //CustomerStatementValidator.getInvalidCustomerStatements(roboCSVReader.getCustomerStatements()).forEach(System.out::println);
        //CustomerStatementValidator.getInvalidCustomerStatements(roboCSVReader.getCustomerStatements()).forEach(System.out::println);
        CSVWriter.writeInvalidCustomerStatements(CustomerStatementValidator.
                getInvalidCustomerStatements(csvStatementReader.getCustomerStatements()), "InvalidRecordsInCSVFormat.csv");
        CSVWriter.writeInvalidCustomerStatements(CustomerStatementValidator.
                getInvalidCustomerStatements(xmlStatementReader.getCustomerStatements()), "InvalidRecordsInXMLFormat.csv");
    }
}
