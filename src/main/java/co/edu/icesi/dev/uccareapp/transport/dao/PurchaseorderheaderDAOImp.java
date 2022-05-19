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
@Transactional
public class PurchaseorderheaderDAOImp implements PurchaseorderheaderDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(*) FROM Purchaseorderheader", Long.class).getSingleResult();
    }

    @Override
    public void save(Purchaseorderheader entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Purchaseorderheader entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Purchaseorderheader entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Purchaseorderheader").executeUpdate();
    }

    @Override
    public Optional<Purchaseorderheader> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Purchaseorderheader.class, id));
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent()? true : false;
    }

    @Override
    public Iterable<Purchaseorderheader> findAll() {
        String jpql = "SELECT h FROM Purchaseorderheader h";
        return entityManager.createQuery(jpql, Purchaseorderheader.class).getResultList();
    }

    @Override
    public Iterable<Purchaseorderheader> findByShipmethodid(Integer shipmethodid) {
        String jpql = "SELECT h FROM Purchaseorderheader h WHERE h.shipmethod.shipmethodid = :shipmethodid";
        TypedQuery<Purchaseorderheader> query = entityManager.createQuery(jpql, Purchaseorderheader.class);
        query.setParameter("shipmethodid", shipmethodid);
        return query.getResultList();
    }

    @Override
    public Iterable<Purchaseorderheader> findByVendorid(Integer vendorid) {
        String jpql = "SELECT h FROM Purchaseorderheader h WHERE h.vendor.businessentityid = :vendorid";
        TypedQuery<Purchaseorderheader> query = entityManager.createQuery(jpql, Purchaseorderheader.class);
        query.setParameter("vendorid", vendorid);
        return query.getResultList();
    }

    public Iterable<Purchaseorderheader> findByDateRange(LocalDate start, LocalDate end) {
        String jpql = "SELECT h FROM Purchaseorderheader h,  Purchaseorderdetail d " +
                      "WHERE h.id.purchaseorderid = d.purchaseorderid " +
                      "AND d.duedate >= :start AND d.duedate <= :end";
        TypedQuery<Purchaseorderheader> query = entityManager.createQuery(jpql, Purchaseorderheader.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }
}