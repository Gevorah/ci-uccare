package co.edu.icesi.dev.uccareapp.transport.dao;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Billofmaterial;

import java.util.Optional;

public interface BillofmaterialDAO {
    
    long count();
    void save(Billofmaterial entity);
    void update(Billofmaterial entity);
    void delete(Billofmaterial entity);
    Optional<Billofmaterial> findById(Integer id);
    boolean existsById(Integer id);
    Iterable<Billofmaterial> findAll();
}
