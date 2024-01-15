package dambi.springFutbolariak.model;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface FutbolariakRepository {
    //GET

    List<Futbolariak> findAll();

    Futbolariak findById(String futbolariId);
    
    List<Futbolariak> findByNationality(String nationality);

    //DELETE

    void deleteById(String futbolariId);

    void deleteByName(String name);

    //PUT

    Futbolariak save(Futbolariak person);
    
   

}
