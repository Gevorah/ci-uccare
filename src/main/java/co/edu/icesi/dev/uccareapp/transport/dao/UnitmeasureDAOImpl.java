package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Unitmeasure;

@Repository
@Transactional
public class UnitmeasureDAOImpl implements UnitmeasureDAO{
	
	 @PersistenceContext
	    private EntityManager entityManager;

	    @Override
	    public long count() {
	        return entityManager.createQuery("SELECT COUNT(*) FROM Unitmeasure", Long.class).getSingleResult();
	    }

	    @Override
	    public void save(Unitmeasure entity) {
	        entityManager.persist(entity);
	        
	    }

	    @Override
	    public void update(Unitmeasure entity) {
	        entityManager.merge(entity);
	        
	    }

	    @Override
	    public void delete(Unitmeasure entity) {
	        entityManager.remove(entity);
	        
	    }

	    @Override
	    public Optional<Unitmeasure> findById(String id) {
	        return Optional.ofNullable(entityManager.find(Unitmeasure.class, id));
	    }

	    @Override
	    public boolean existsById(String id) {
	        return findById(id).isPresent()? true : false;
	    }

	    @Override
	    public Iterable<Unitmeasure> findAll() {
	        String jpql = "SELECT d FROM Unitmeasure d";
	        return entityManager.createQuery(jpql, Unitmeasure.class).getResultList();
	    }

}
