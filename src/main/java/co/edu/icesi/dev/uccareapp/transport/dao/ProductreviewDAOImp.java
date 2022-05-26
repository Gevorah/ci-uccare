package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Productreview;

@Repository
@Transactional
public class ProductreviewDAOImp implements ProductreviewDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(*) FROM Productreview", Long.class).getSingleResult();
    }

    @Override
    public void save(Productreview entity) {
        entityManager.persist(entity);
        
    }

    @Override
    public void update(Productreview entity) {
        entityManager.merge(entity);
        
    }

    @Override
    public void delete(Productreview entity) {
        entityManager.remove(entity);
        
    }

    @Override
    public Optional<Productreview> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Productreview.class, id));
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent()? true : false;
    }

    @Override
    public Iterable<Productreview> findAll() {
        String jpql = "SELECT d FROM Billofmaterial d";
        return entityManager.createQuery(jpql, Productreview.class).getResultList();
    }
}
