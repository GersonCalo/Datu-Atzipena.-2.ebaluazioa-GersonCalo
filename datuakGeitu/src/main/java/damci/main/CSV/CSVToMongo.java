package damci.main.csv;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;
/*Este programa Java carga datos desde un archivo CSV a una base de datos MongoDB. 
Lee el archivo CSV, crea documentos MongoDB a partir de los datos y los inserta en una colección de la base de datos MongoDB. 
El programa utiliza try-with-resources para gestionar la conexión y garantizar la liberación adecuada de recursos.*/
public class CSVToMongo {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("futbolariakDataBasetest");
            MongoCollection<Document> collection = database.getCollection("futbolariak");

            Path csvFilePath = Paths.get(
                    "C:\\Users\\gerson\\Documents\\DatuAtzipenaDoc\\Datu-Atzipena.-2.ebaluazioa-GersonCalo\\data\\fifa_test.csv");

            try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath.toFile()))) {
                List<String[]> records = csvReader.readAll();

                String[] columnNames = records.get(0);

                for (int i = 1; i < records.size(); i++) {
                    String[] record = records.get(i);
                    System.out.println("Procesando registro " + i + ": " + Arrays.toString(record));
                    Document document = new Document();
                    for (int j = 0; j < columnNames.length; j++) {
                        String columnName = columnNames[j];
                        String value = record[j];

                        switch (columnName) {
                            case "positions":
                                // Dividir la cadena de posiciones por comas y almacenar en un array
                                String[] positionsArray = value.split(",");
                                document.append(columnName, Arrays.asList(positionsArray));
                                break;
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
                            default:
                                document.append(columnName, value);
                                break;
                        }
                    }
                    collection.insertOne(document);

                    System.out.println("Documento insertado en MongoDB:");
                    System.out.println(document.toJson());
                    System.out.println("---------------------------------");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
