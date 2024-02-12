package damci.main.csv;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVWriter;
import org.bson.Document;

public class MongoToCSVExporter {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("futbolariakNationaityDataBaseTest");
            MongoCollection<Document> collection = database.getCollection("Argentina");
            Path csvFilePath = Paths.get(
                    "C:\\Users\\gerson\\Documents\\DatuAtzipenaDoc\\Datu-Atzipena.-2.ebaluazioa-GersonCalo\\data\\csv\\mongoToCSV.csv");
            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFilePath.toFile()))) {
                FindIterable<Document> documents = collection.find();
                Document firstDocument = documents.first();
                if (firstDocument != null) {
                    List<String> columnNames = firstDocument.keySet().stream()
                            .filter(key -> key instanceof String && !key.equals("_id"))
                            .map(Object::toString)
                            .toList();
                    csvWriter.writeNext(columnNames.toArray(new String[0]));
                    for (Document document : documents) {
                        List<String> rowData = columnNames.stream()
                                .map(columnName -> {
                                    Object value = document.get(columnName);
                                    return (value != null) ? value.toString() : "";
                                })
                                .toList();
                        csvWriter.writeNext(rowData.toArray(new String[0]));
                    }
                    System.out.println("Exportación a CSV completada con éxito.");
                } else {
                    System.out.println("La colección está vacía. No hay datos para exportar.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}