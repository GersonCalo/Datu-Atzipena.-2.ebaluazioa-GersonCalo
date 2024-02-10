package dambi.springFutbolariak.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FutbolariakRepository {
    //GET

    List<Futbolariak> findAll();

    Futbolariak findById(String futbolariId);
    
    List<Futbolariak> findByNationalityAndPosition(String nationality, String position);

    //DELETE

    void deleteById(String futbolariId);

    @Query("{'player.name': ?0, 'player.birth_date': ?1}")
    void deleteByNameAndBirthDate(String name,String birthDate);

    //PUT

    Futbolariak save(Futbolariak person);
    
    Futbolariak update(ObjectId id,double velue);
}
