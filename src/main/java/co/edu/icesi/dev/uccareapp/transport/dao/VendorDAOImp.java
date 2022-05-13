package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;

@Repository
public class VendorDAOImp implements VendorDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Vendor entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Vendor entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Vendor entity) {
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Vendor").executeUpdate();
    }

    @Override
    public Optional<Vendor> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Vendor.class, id));
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent()? true : false;
    }

    @Override
    public Iterable<Vendor> findAll() {
        String psql = "SELECT v FROM Vendor v";
        return (Iterable<Vendor>) entityManager.createQuery(psql).getResultList();
    }

    @Override
    public Iterable<Vendor> findByCreditrating(Integer creditrating) {
        String psql = "SELECT v FROM Vendor v WHERE v.creditrating = " + creditrating;
        return (Iterable<Vendor>) entityManager.createQuery(psql).getResultList();
    }

    @Override
    public Iterable<Vendor> findByName(String name) {
        String psql = "SELECT v FROM Vendor v WHERE v.name = " + name;
        return (Iterable<Vendor>) entityManager.createQuery(psql).getResultList();
    }

    @Override
    public Iterable<Vendor> findByPreferredvendorstatus(String preferredvendorstatus) {
        String psql = "SELECT v FROM Vendor v WHERE v.preferredvendorstatus = " + preferredvendorstatus;
        return (Iterable<Vendor>) entityManager.createQuery(psql).getResultList();
    }
}
