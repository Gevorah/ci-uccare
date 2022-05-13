package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;

public interface ShipmethodDAO {
    
    void save(Shipmethod entity);
    void update(Shipmethod entity);
    void delete(Shipmethod entity);
    void deleteAll();
    Optional<Shipmethod> findById(Integer id);
    boolean existsById(Integer id);
    Iterable<Shipmethod> findAll();
}
