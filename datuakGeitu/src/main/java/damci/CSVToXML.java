package damci;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVToXML {
    public static void main(String[] args) {
        try {
            Path csvFilePath = Paths.get("C:\\Users\\gerson\\Documents\\DatuAtzipenaDoc\\Datu-Atzipena.-2.ebaluazioa-GersonCalo\\data\\fifa_test.csv"); // Reemplaza con la ruta de tu archivo CSV

            CSVReader csvReader = new CSVReader(new FileReader(csvFilePath.toFile()));

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("futbolariak");
            document.appendChild(rootElement);

            List<String[]> records = csvReader.readAll();
            String[] columnNames = records.get(0);

            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);

                Element recordElement = document.createElement("futbolaria");
                rootElement.appendChild(recordElement);

                for (int j = 0; j < columnNames.length; j++) {
                    String columnName = sanitizeXmlName(columnNames[j]);  // Reemplaza caracteres no válidos
                    String value = record[j];

                    Element fieldElement = document.createElement(columnName);
                    fieldElement.appendChild(document.createTextNode(value));
                    recordElement.appendChild(fieldElement);
                }
            }
            csvReader.close();
            // Guardar el documento XML en un archivo
            FileWriter writer = new FileWriter("C:\\Users\\gerson\\Documents\\DatuAtzipenaDoc\\Datu-Atzipena.-2.ebaluazioa-GersonCalo\\data\\fifa_test.xml"); // Reemplaza con la ruta de salida del archivo XML
            writeXmlDocument(document, writer);
            writer.close();
            System.out.println("Archivo XML generado exitosamente.");
        } catch (IOException | CsvValidationException | RuntimeException | ReflectiveOperationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sanitizeXmlName(String name) {
        // Reemplaza caracteres no válidos en nombres de elementos XML
        return name.replaceAll("[^a-zA-Z0-9_]", "_");
    }

    private static void writeXmlDocument(Document document, FileWriter writer) throws Exception {
        javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
        javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
        javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
        javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(writer);
        transformer.transform(source, result);
    }
}
