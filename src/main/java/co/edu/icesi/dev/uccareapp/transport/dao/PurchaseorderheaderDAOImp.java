package co.edu.icesi.dev.uccareapp.transport.dao;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;

@Repository
public class PurchaseorderheaderDAOImp implements PurchaseorderheaderDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Purchaseorderheader entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void update(Purchaseorderheader entity) {
        entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(Purchaseorderheader entity) {
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Purchaseorderheader").executeUpdate();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Purchaseorderheader> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Purchaseorderheader.class, id));
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent()? true : false;
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Purchaseorderheader> findAll() {
        String jpql = "SELECT h FROM Purchaseorderheader h";
        return entityManager.createQuery(jpql, Purchaseorderheader.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Purchaseorderheader> findByShipmethodid(Integer shipmethodid) {
        String jpql = "SELECT h FROM Purchaseorderheader h WHERE h.shipmethod.shipmethodid = :shipmethodid";
        TypedQuery<Purchaseorderheader> query = entityManager.createQuery(jpql, Purchaseorderheader.class);
        query.setParameter("shipmethodid", shipmethodid);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Purchaseorderheader> findByVendorid(Integer vendorid) {
        String jpql = "SELECT h FROM Purchaseorderheader h WHERE h.vendor.businessentityid = :vendorid";
        TypedQuery<Purchaseorderheader> query = entityManager.createQuery(jpql, Purchaseorderheader.class);
        query.setParameter("vendorid", vendorid);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public Iterable<Purchaseorderheader> findByDateRange(LocalDate start, LocalDate end) {
        String jpql = "";
        TypedQuery<Purchaseorderheader> query = entityManager.createQuery(jpql, Purchaseorderheader.class);
        query.setParameter("start", start);
        query.setParameter("end", start);
        return query.getResultList();
    }
}
