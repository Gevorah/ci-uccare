package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;

@Repository
public class VendorDAOImp implements VendorDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Vendor entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void update(Vendor entity) {
        entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(Vendor entity) {
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Vendor").executeUpdate();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Vendor> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Vendor.class, id));
    }

    ///////////////////////////////////////////////////////////////////////////////////
    @Transactional(readOnly = true)
    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent()? true : false;
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Vendor> findAll() {
        String jpql = "SELECT v FROM Vendor v";
        return entityManager.createQuery(jpql, Vendor.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Vendor> findByCreditrating(Integer creditrating) {
        String jpql = "SELECT v FROM Vendor v WHERE v.creditrating = :creditrating";
        TypedQuery<Vendor> query = entityManager.createQuery(jpql, Vendor.class);
        query.setParameter("creditrating", creditrating);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Vendor> findByName(String name) {
        String jpql = "SELECT v FROM Vendor v WHERE v.name = :name";
        TypedQuery<Vendor> query = entityManager.createQuery(jpql, Vendor.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Vendor> findByPreferredvendorstatus(String preferredvendorstatus) {
        String jpql = "SELECT v FROM Vendor v WHERE v.preferredvendorstatus = :preferredvendorstatus";
        TypedQuery<Vendor> query = entityManager.createQuery(jpql, Vendor.class);
        query.setParameter("preferredvendorstatus", preferredvendorstatus);
        return query.getResultList();
    }
}
