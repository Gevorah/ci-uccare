package co.edu.icesi.dev.uccareapp.transport.dao;

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

    @Transactional(readOnly = true)
    @Override
    public Iterable<Purchaseorderheader> findAll() {
        String jpql = "SELECT h FROM Purchaseorderheader h";
        return entityManager.createQuery(jpql, Purchaseorderheader.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Purchaseorderheader> findByShipmethodid(Integer shipmethodid) {
        String jpql = "SELECT h FROM Purchaseorderheader h WHERE h.shipmethodid = :shipmethodid";
        TypedQuery<Purchaseorderheader> query = entityManager.createQuery(jpql, Purchaseorderheader.class);
        query.setParameter("shipmethodid", shipmethodid);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Purchaseorderheader> findByVendorid(Integer vendorid) {
        String jpql = "SELECT h FROM Purchaseorderheader h WHERE h.vendorid = :vendorid";
        TypedQuery<Purchaseorderheader> query = entityManager.createQuery(jpql, Purchaseorderheader.class);
        query.setParameter("vendorid", vendorid);
        return query.getResultList();
    }
}
