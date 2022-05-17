package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;

@Repository
public class ShipmethodDAOImp implements ShipmethodDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Shipmethod entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Shipmethod entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Shipmethod entity) {
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Shipmethod").executeUpdate();
    }

    @Override
    public Optional<Shipmethod> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Shipmethod.class, id));
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent()? true : false;
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Shipmethod> findAll() {
        String jpql = "SELECT s FROM Shipmethod s";
        return entityManager.createQuery(jpql, Shipmethod.class).getResultList();
    }
}
