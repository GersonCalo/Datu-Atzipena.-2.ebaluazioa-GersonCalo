package damci;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVToJSON {
    public static void main(String[] args) {
        try {
            Path csvFilePath = Paths.get("C:\\Users\\gerson\\Documents\\DatuAtzipenaDoc\\Datu-Atzipena.-2.ebaluazioa-GersonCalo\\data\\fifa_test.csv"); // Reemplaza con la ruta de tu archivo CSV

            CSVReader csvReader = new CSVReader(new FileReader(csvFilePath.toFile()));

            List<String[]> records = csvReader.readAll();
            String[] columnNames = records.get(0);

            // Configura ObjectMapper de Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            // Crea un objeto JSON que contendrá todos los registros
            ArrayNode jsonArray = objectMapper.createArrayNode();

            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);

                // Crea un objeto para cada fila
                ObjectNode rowObject = objectMapper.createObjectNode();

                for (int j = 0; j < columnNames.length; j++) {
                    String columnName = sanitizeJsonName(columnNames[j]);  // Reemplaza caracteres no válidos
                    String value = record[j];

                    // Agrega la columna y su valor al objeto JSON
                    rowObject.put(columnName, value);
                }

                // Agrega el objeto de fila al array JSON
                jsonArray.add(rowObject);
            }

            // Convierte el array JSON a formato de cadena
            String jsonString = jsonArray.toString();

            // Guarda el objeto JSON en un archivo
            FileWriter jsonWriter = new FileWriter("C:\\Users\\gerson\\Documents\\DatuAtzipenaDoc\\Datu-Atzipena.-2.ebaluazioa-GersonCalo\\data\\fifa_test.json");
            jsonWriter.write(jsonString);
            jsonWriter.close();

            csvReader.close();
            System.out.println("Archivo JSON generado exitosamente.");
        } catch (IOException | CsvValidationException | RuntimeException  e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sanitizeJsonName(String name) {
        // Reemplaza caracteres no válidos en nombres de propiedades JSON
        return name.replaceAll("[^a-zA-Z0-9_]", "_");
    }
}
