package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;

@Repository
@Transactional
public class ProductDAOImp implements ProductDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(*) FROM Product", Long.class).getSingleResult();
    }

    @Override
    public void save(Product entity) {
        entityManager.persist(entity);
        
    }

    @Override
    public void update(Product entity) {
        entityManager.merge(entity);
        
    }

    @Override
    public void delete(Product entity) {
        entityManager.remove(entity);
        
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent()? true : false;
    }

    @Override
    public Iterable<Product> findAll() {
        String jpql = "SELECT d FROM Product d";
        return entityManager.createQuery(jpql, Product.class).getResultList();
    }
}
