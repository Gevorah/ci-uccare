package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;

public interface ProductDAO {
    long count();
    void save(Product entity);
    void update(Product entity);
    void delete(Product entity);
    Optional<Product> findById(Integer id);
    boolean existsById(Integer id);
    Iterable<Product> findAll();
}
