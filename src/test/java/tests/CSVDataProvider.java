package tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataProvider {
    @DataProvider(name = "csvData")
    public Object[][] readCSVData() throws IOException, CsvValidationException {
        String filePath = "src/data/user.csv";
        List<String[]> csvData = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] data;
            while ((data = csvReader.readNext()) != null) {
                csvData.add(data);
            }
        }

        // Convert List<String[]> to Object[][]
        Object[][] testData = new Object[csvData.size()][];
        for (int i = 0; i < csvData.size(); i++) {
            testData[i] = csvData.get(i);
        }

        return testData;
    }
}
