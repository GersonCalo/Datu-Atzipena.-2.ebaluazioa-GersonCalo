package damci.main.json.toMongo;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class JSONToMongo {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("futbolariakDataBase");
            MongoCollection<Document> collection = database.getCollection("futbolariak");

            Path jsonFilePath = Paths.get(
                    "C:\\Users\\gerson\\Documents\\DatuAtzipenaDoc\\Datu-Atzipena.-2.ebaluazioa-GersonCalo\\data\\json\\fifa_players.json");

            try (FileReader fileReader = new FileReader(jsonFilePath.toFile())) {
                JsonArray jsonArray = JsonParser.parseReader(fileReader).getAsJsonArray();

                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                    JsonObject playerObject = jsonObject.getAsJsonObject("player");

                    Document document = new Document();
                    document.append("player", new Document("name", playerObject.get("name").getAsString())
                            .append("full_name", playerObject.get("full_name").getAsString())
                            .append("birth_date", playerObject.get("birth_date").getAsString())
                            .append("age", playerObject.get("age").getAsInt())
                            .append("height_cm", playerObject.get("height_cm").getAsDouble())
                            .append("weight_kgs", playerObject.get("weight_kgs").getAsDouble()));

                    for (String fieldName : jsonObject.keySet()) {
                        if (!fieldName.equals("player")) {
                            switch (fieldName) {
                                case "positions":
                                    // Dividir la cadena de posiciones por comas y almacenar en un array
                                    String[] positionsArray = jsonObject.getAsJsonArray(fieldName).toString()
                                            .replace("[", "").replace("]", "").split(",");
                                    document.append(fieldName, Arrays.asList(positionsArray));
                                    break;
                                case "overall_rating":
                                case "potential":
                                case "international_reputation(1-5)":
                                case "weak_foot(1-5)":
                                case "skill_moves(1-5)":
                                case "national_rating":
                                case "crossing":
                                    // Agrega otros campos según sea necesario
                                    String stringValue = jsonObject.getAsJsonPrimitive(fieldName).getAsString();
                                    int intValue;
                                    if (stringValue.isEmpty()) {
                                        intValue = 0; // O asigna otro valor predeterminado
                                    } else {
                                        intValue = Integer.parseInt(stringValue);
                                    }
                                    document.append(fieldName, intValue);
                                    break;
                                case "height_cm":
                                case "weight_kgs":
                                case "value_euro":
                                case "wage_euro":
                                case "release_clause_euro":
                                    // Agrega otros campos según sea necesario
                                    // Agrega otros campos según sea necesario
                                    String stringValueDouble = jsonObject.getAsJsonPrimitive(fieldName).getAsString();
                                    double doubleValue;
                                    if (stringValueDouble.isEmpty()) {
                                        doubleValue = 0.0; // O asigna otro valor predeterminado
                                    } else {
                                        doubleValue = Double.parseDouble(stringValueDouble);
                                    }
                                    document.append(fieldName, doubleValue);
                                    break;
                                default:
                                    document.append(fieldName, jsonObject.getAsJsonPrimitive(fieldName).getAsString());
                                    break;
                            }
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
