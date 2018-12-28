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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class created to read the CSV file and map them into Customer statement POJO
 */
public class CSVStatementReader {
    private Logger logger = Logger.getLogger(CSVStatementReader.class.getName());

    public List<CustomerStatement> getCustomerStatements() {

        Path path;
        List<CustomerStatement> customerStatements = null;
        Stream<String> fileStream = null;
        try {
            path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                    .getResource("com/robobank/resources/records.csv")).toURI());
            fileStream = Files.lines(path);

            customerStatements = fileStream.skip(1).map(mapToCustomerStatement).collect(Collectors.toList());

        } catch (URISyntaxException | IOException e) {
            logger.log(Level.SEVERE, " Exception occurred while processing CSV file " + e.getMessage());
        } finally {
            Objects.requireNonNull(fileStream).close();
        }

        return customerStatements;
    }

    private Function<String, CustomerStatement> mapToCustomerStatement = line -> {
        String[] p = line.split(",");
        return new CustomerStatement(Integer.parseInt(p[0]), p[1], p[2], new BigDecimal(p[3]),
                new BigDecimal(p[4]), new BigDecimal(p[5]));
    };
}
