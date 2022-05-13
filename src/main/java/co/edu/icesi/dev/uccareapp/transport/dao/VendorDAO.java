package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;

public interface VendorDAO {

    void save(Vendor entity);
    void update(Vendor entity);
    void delete(Vendor entity);
    void deleteAll();
    Optional<Vendor> findById(Integer id);
    boolean existsById(Integer id);
    Iterable<Vendor> findAll();

    Iterable<Vendor> findByCreditrating(Integer creditrating);
    Iterable<Vendor> findByName(String name);
    Iterable<Vendor> findByPreferredvendorstatus(String preferredvendorstatus);
}
