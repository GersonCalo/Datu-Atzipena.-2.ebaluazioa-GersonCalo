package dambi.springFutbolariak.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import static com.mongodb.client.model.Updates.set;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;

@Repository
public class MongoDBFutbolariakRepository implements FutbolariakRepository {

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();
    @Autowired
    private MongoClient client;
    private MongoCollection<Futbolariak> futbolariakCollection;

    @PostConstruct
    void init() {
        futbolariakCollection = client.getDatabase("futbolariakDataBase").getCollection("futbolariak",
                Futbolariak.class);
    }

    // GET
    /**
 * Devuelve una lista que contiene todos los futbolistas presentes en la colección futbolariakCollection.
 * Realiza una búsqueda sin filtros y convierte los resultados en una ArrayList de objetos Futbolariak antes de devolverla.
 */

    @Override
    public List<Futbolariak> findAll() {
        return futbolariakCollection.find().into(new ArrayList<>());
    }

    /**
 * Busca y devuelve un futbolista en la colección futbolariakCollection basado en el ObjectId proporcionado.
 * Utiliza un filtro para encontrar el documento con el ObjectId correspondiente y devuelve el primer resultado encontrado.
 * Si no se encuentra ningún futbolista, devuelve null.
 */

    @Override
    public Futbolariak findById(String id) {
        return futbolariakCollection.find(eq("_id", new ObjectId(id))).first();
    }

    /**
 * Busca y devuelve una lista de futbolistas en la colección futbolariakCollection que coinciden con la nacionalidad y posición especificadas.
 * Utiliza un filtro Bson que combina igualdad en la nacionalidad y verifica si la posición está presente en la lista de posiciones del futbolista.
 * La lista resultante se convierte en una ArrayList de objetos Futbolariak y se devuelve.
 */

    @Override
    public List<Futbolariak> findByNationalityAndPosition(String nationality, String position) {
        String formatPsition = '"' + position + '"';

        Bson filter = and(eq("nationality", nationality), in("positions", formatPsition));
        return futbolariakCollection.find(filter).into(new ArrayList<>());
    }

    // DELETE
    /**
 * Elimina un documento de la colección futbolariakCollection basado en el ObjectId proporcionado.
 * Convierte la cadena futbolariId a un ObjectId y utiliza un filtro para encontrar y eliminar el documento correspondiente.
 * Imprime un mensaje en la consola antes de realizar la eliminación.
 */

    @Override
    public void deleteById(String futbolariId) {
        System.out.println("entra al borar por id ");
        futbolariakCollection.deleteOne(eq("_id", new ObjectId(futbolariId)));
    }

    /**
 * Elimina todos los documentos de la colección futbolariakCollection que coinciden con el nombre y la fecha de nacimiento proporcionados.
 * Utiliza un filtro Bson que combina igualdad en el nombre del jugador y la fecha de nacimiento para identificar los documentos a eliminar.
 */

    @Override
    public void deleteByNameAndBirthDate(String name, String birthDate) {
        Bson filter = and(eq("player.name", name), eq("player.birth_date", birthDate));
        futbolariakCollection.deleteMany(filter);
    }

    // POST
    /**
 * Guarda un nuevo objeto Futbolariak en la colección.
 * Asigna un nuevo ObjectId al futbolista antes de insertarlo en la colección futbolariakCollection.
 * Devuelve el objeto Futbolariak con el nuevo ObjectId asignado después de la inserción.
 */

    @Override
    public Futbolariak save(Futbolariak futbolariak) {
        futbolariak.setId(new ObjectId());
        futbolariakCollection.insertOne(futbolariak);
        return futbolariak;
    }

    /**
 * Actualiza el valor en euros de un futbolista identificado por su ObjectId.
 * Utiliza la colección de futbolistas (futbolariakCollection) para buscar y actualizar el valor.
 * Devuelve el objeto Futbolariak actualizado si la actualización tiene éxito; de lo contrario, devuelve null.
 */

    @Override
    public Futbolariak update(ObjectId objectId, double newValueEuro) {
        try {
            UpdateResult updateResult = futbolariakCollection.updateOne(
                    eq("_id", objectId),
                    set("value_euro", newValueEuro));
            if (updateResult.getModifiedCount() > 0) {
                return futbolariakCollection.find(eq("_id", objectId)).first();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
