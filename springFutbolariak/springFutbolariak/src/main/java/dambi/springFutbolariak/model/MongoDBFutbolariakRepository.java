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
    @Override
    public List<Futbolariak> findAll() {
        return futbolariakCollection.find().into(new ArrayList<>());
    }

    @Override
    public Futbolariak findById(String id) {
        return futbolariakCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public List<Futbolariak> findByNationalityAndPosition(String nationality, String position) {
        String formatPsition = '"' + position + '"';

        Bson filter = and(eq("nationality", nationality), in("positions", formatPsition));
        return futbolariakCollection.find(filter).into(new ArrayList<>());
    }

    // DELETE
    @Override
    public void deleteById(String futbolariId) {
        System.out.println("entra al borar por id ");
        futbolariakCollection.deleteOne(eq("_id", new ObjectId(futbolariId)));
    }

    @Override
    public void deleteByNameAndBirthDate(String name, String birthDate) {
        Bson filter = and(eq("player.name", name), eq("player.birth_date", birthDate));
        futbolariakCollection.deleteMany(filter);
    }

    // POST
    @Override
    public Futbolariak save(Futbolariak futbolariak) {

        futbolariakCollection.insertOne(futbolariak);
        return futbolariak;
    }
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
