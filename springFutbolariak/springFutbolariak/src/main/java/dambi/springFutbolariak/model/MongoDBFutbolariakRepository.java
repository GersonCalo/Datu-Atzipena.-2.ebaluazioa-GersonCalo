package dambi.springFutbolariak.model;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoDBFutbolariakRepository implements FutbolariakRepository {

    private static final  TransactionOptions txnOptions = TransactionOptions.builder()
                                                                            .readPreference(ReadPreference.primary())
                                                                            .readConcern(ReadConcern.MAJORITY)
                                                                            .writeConcern(WriteConcern.MAJORITY)
                                                                            .build();
    @Autowired
    private MongoClient client;
    private MongoCollection<Futbolariak> futbolariakCollection;

    @PostConstruct
    void init(){
        futbolariakCollection = client.getDatabase("futbolariakDataBase").getCollection("futbolariak",Futbolariak.class);
    }

    @Override
    public List<Futbolariak> findAll(){
        return futbolariakCollection.find().into(new ArrayList<>());
    }

    @Override
    public Futbolariak findById(String id){
        return futbolariakCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public List<Futbolariak> findByNationality(String nationality) {
        return futbolariakCollection.find(eq("nationality", nationality)).into(new ArrayList<>());
    }

    @Override
    public  void deleteById(String futbolariId){
        futbolariakCollection.deleteOne(eq("_id",new ObjectId(futbolariId)));
    }

    @Override
    public void deleteByName(String name){
        futbolariakCollection.deleteMany(eq("name",name));
    }


    @Override
    public Futbolariak save(Futbolariak futbolariak){
        futbolariak.setId(new ObjectId());
        futbolariakCollection.insertOne(futbolariak);
        return futbolariak;
    }

        
}
