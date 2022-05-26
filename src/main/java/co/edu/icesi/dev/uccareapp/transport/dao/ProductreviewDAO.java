package co.edu.icesi.dev.uccareapp.transport.dao;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Productreview;

import java.util.Optional;

public interface ProductreviewDAO {

    long count();
    void save(Productreview entity);
    void update(Productreview entity);
    void delete(Productreview entity);
    Optional<Productreview> findById(Integer id);
    boolean existsById(Integer id);
    Iterable<Productreview> findAll();
    
}
