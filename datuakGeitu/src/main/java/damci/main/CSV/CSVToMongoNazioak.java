package damci.main.CSV;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;

public class CSVToMongoNazioak {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, proporciona la lista de nacionalidades como argumento.");
            return;
        }

        Set<String> allowedNationalities = new HashSet<>(Arrays.asList(args));
        boolean foundRecords = false; // Variable para verificar si se encontraron registros

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("futbolariakNationaityDataBaseTest");

            Path csvFilePath = Paths.get(
                    "C:\\Users\\gerson\\Documents\\DatuAtzipenaDoc\\Datu-Atzipena.-2.ebaluazioa-GersonCalo\\data\\fifa_test.csv");

            try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath.toFile()))) {
                List<String[]> records = csvReader.readAll();

                String[] columnNames = records.get(0);

                // Declarar la colección fuera del bucle for
                MongoCollection<Document> collection = null;

                for (int i = 1; i < records.size(); i++) {
                    String[] record = records.get(i);
                    String nationality = record[Arrays.asList(columnNames).indexOf("nationality")];

                    if (!allowedNationalities.contains(nationality)) {
                        // La nacionalidad no está en la lista permitida, continuar con el siguiente
                        // registro
                        continue;
                    }

                    foundRecords = true; // Se encontraron registros
                    System.out.println("Procesando registro " + i + ": " + Arrays.toString(record));
                    Document document = new Document();

                    for (int j = 0; j < columnNames.length; j++) {
                        String columnName = columnNames[j];
                        String value = record[j];

                        switch (columnName) {
                            case "age":
                            case "overall_rating":
                            case "potential":
                            case "international_reputation":
                            case "weak_foot":
                            case "skill_moves":
                                int intValue = Integer.parseInt(value);
                                document.append(columnName, intValue);
                                break;
                            case "height_cm":
                            case "weight_kgs":
                            case "value_euro":
                            case "wage_euro":
                                if (!value.isEmpty()) {
                                    double doubleValue = Double.parseDouble(value);
                                    document.append(columnName, doubleValue);
                                } else {
                                    document.append(columnName, 0.0);
                                }
                                break;
                            case "nationality":
                                // Actualizar la colección según el valor del campo "nationality"
                                collection = database.getCollection(value);
                                document.append(columnName, value);
                                break;
                            default:
                                document.append(columnName, value);
                                break;
                        }
                    }
                    if (collection != null) {
                        collection.insertOne(document);
                    }

                    System.out.println("Documento insertado en MongoDB:");
                    System.out.println(document.toJson());
                    System.out.println("---------------------------------");
                }

                if (!foundRecords) {
                    System.out.println("No se encontraron registros para las nacionalidades proporcionadas.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
