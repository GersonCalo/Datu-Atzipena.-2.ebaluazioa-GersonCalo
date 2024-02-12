package damci.main.xml.mongo;

import org.bson.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class XMLToMongo {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("futbolariakDataBaseFromXML");
            MongoCollection<Document> collection = database.getCollection("futbolariakxml");

            Path xmlFilePath = Paths.get("C:\\Users\\gerson\\Documents\\DatuAtzipenaDoc\\Datu-Atzipena.-2.ebaluazioa-GersonCalo\\data\\xml\\fifa_test.xml");  // Reemplaza con la ruta real

            List<Document> documents = parseXML(xmlFilePath);
            collection.insertMany(documents);

            System.out.println("Documentos insertados en MongoDB.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Document> parseXML(Path xmlFilePath) {
        List<Document> documents = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(xmlFilePath.toFile());

            NodeList playersList = document.getElementsByTagName("players");

            for (int i = 0; i < playersList.getLength(); i++) {
                Node playersNode = playersList.item(i);

                if (playersNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element playersElement = (Element) playersNode;

                    NodeList playerNodes = playersElement.getElementsByTagName("player");

                    for (int j = 0; j < playerNodes.getLength(); j++) {
                        Node playerNode = playerNodes.item(j);

                        if (playerNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element playerElement = (Element) playerNode;

                            // Crear un nuevo documento MongoDB para cada jugador
                            Document playerDocument = new Document();
                            NodeList playerFields = playerElement.getChildNodes();

                            for (int k = 0; k < playerFields.getLength(); k++) {
                                Node fieldNode = playerFields.item(k);

                                if (fieldNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element fieldElement = (Element) fieldNode;
                                    String fieldName = fieldElement.getNodeName();
                                    String fieldValue = fieldElement.getTextContent();

                                    // Manejar campos anidados de manera diferente
                                    if (fieldName.equals("positions") || fieldName.startsWith("international_reputation_")
                                            || fieldName.startsWith("weak_foot_") || fieldName.startsWith("skill_moves_")) {
                                        // Puedes procesar estos campos anidados de manera diferente si es necesario
                                        // Por ejemplo, agregar a un campo anidado en el documento MongoDB
                                        Document nestedField = new Document(fieldName, fieldValue);
                                        playerDocument.append("nestedFields", nestedField);
                                    } else {
                                        // Agregar campos al documento MongoDB
                                        playerDocument.append(fieldName, fieldValue);
                                    }
                                }
                            }

                            // Agregar el documento del jugador a la lista
                            documents.add(playerDocument);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return documents;
    }
}
