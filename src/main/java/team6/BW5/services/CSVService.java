package team6.BW5.services;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class CSVService {

    public List<String[]> convertCSV(Path path) throws IOException {
        try (Reader reader = Files.newBufferedReader(path);
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                     .withSkipLines(1)
                     .build()) {
            return csvReader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error reading CSV file: " + e.getMessage(), e);
        }
    }

}





