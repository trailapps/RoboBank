package com.robobank.reader;

import com.robobank.model.CustomerStatement;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class created to read the CSV file and map them into Customer statement POJO
 */
public class CSVStatementReader {

    public List<CustomerStatement> getCustomerStatements() {

        Path path;
        List<CustomerStatement> customerStatements = null;
        try {
            path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                    .getResource("com/robobank/resources/records.csv")).toURI());
            customerStatements = Files.lines(path).skip(1).map(mapToCustomerStatement).collect(Collectors.toList());

        } catch (URISyntaxException | IOException e) {
            System.out.println(" Exception occurred while processing CSV file " + e.getMessage());
        }

        return customerStatements;
    }

    private Function<String, CustomerStatement> mapToCustomerStatement = (line) -> {
        String[] p = line.split(",");
        return new CustomerStatement(Integer.parseInt(p[0]), p[1], p[2], new BigDecimal(p[3]),
                new BigDecimal(p[4]), new BigDecimal(p[5]));
    };
}
