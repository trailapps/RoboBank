package reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoboCSVReader {

    public static List<String> getData(String titleToSearchFor) throws IOException {
        Path path = Paths.get("arbitoryPath");

        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);

            List<String> columns = Arrays.asList(lines.get(0)
                    .split(","));

            int titleIndex = columns.indexOf(titleToSearchFor);

            List<String> values = lines.stream()
                    .skip(1)
                    .map(line -> Arrays.asList(line.split(",")))
                    .map(list -> list.get(titleIndex))
                    .filter(Objects::nonNull)
                    .filter(s -> s.trim()
                            .length() > 0)
                    .collect(Collectors.toList());

            return values;
        }

        return new ArrayList<>();

    }
}
