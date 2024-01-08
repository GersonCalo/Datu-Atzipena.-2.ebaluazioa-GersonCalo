package dambi.springFutbolariak.model;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface FutbolariakRepository {
    List<Futbolariak> findAll();
    Futbolariak findById(String id);
    Futbolariak save(Futbolariak person);
    long delete(String izena);
}
