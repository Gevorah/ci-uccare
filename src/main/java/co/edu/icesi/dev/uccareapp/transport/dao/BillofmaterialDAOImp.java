package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Billofmaterial;

@Repository
@Transactional
public class BillofmaterialDAOImp implements BillofmaterialDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(*) FROM Billofmaterial", Long.class).getSingleResult();
    }

    @Override
    public void save(Billofmaterial entity) {
        entityManager.persist(entity);
        
    }

    @Override
    public void update(Billofmaterial entity) {
        entityManager.merge(entity);
        
    }

    @Override
    public void delete(Billofmaterial entity) {
        entityManager.remove(entity);
        
    }

    @Override
    public Optional<Billofmaterial> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Billofmaterial.class, id));
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent()? true : false;
    }

    @Override
    public Iterable<Billofmaterial> findAll() {
        String jpql = "SELECT d FROM Billofmaterial d";
        return entityManager.createQuery(jpql, Billofmaterial.class).getResultList();
    }
    
}
