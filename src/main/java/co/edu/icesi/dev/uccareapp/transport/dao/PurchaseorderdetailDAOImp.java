package co.edu.icesi.dev.uccareapp.transport.dao;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;

@Repository
public class PurchaseorderdetailDAOImp implements PurchaseorderdetailDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Purchaseorderdetail entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Purchaseorderdetail entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Purchaseorderdetail entity) {
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Purchaseorderdetail").executeUpdate();
    }

    @Override
    public Optional<Purchaseorderdetail> findById(PurchaseorderdetailPK id) {
        return Optional.ofNullable(entityManager.find(Purchaseorderdetail.class, id));
    }

    @Override
    public boolean existsById(PurchaseorderdetailPK id) {
        return findById(id).isPresent()? true : false;
    }

    @Override
    public Iterable<Purchaseorderdetail> findAll() {
        String psql = "SELECT d FROM Purchaseorderdetail d";
        return (Iterable<Purchaseorderdetail>) entityManager.createQuery(psql).getResultList();
    }

    @Override
    public Iterable<Purchaseorderdetail> findByProductid(Integer productid) {
        String psql = "SELECT d FROM Purchaseorderdetail d WHERE d.productid = " + productid;
        return (Iterable<Purchaseorderdetail>) entityManager.createQuery(psql).getResultList();
    }

    @Override
    public Iterable<Purchaseorderdetail> findByUnitprice(BigDecimal unitprice) {
        String psql = "SELECT d FROM Purchaseorderdetail d WHERE d.unitprice = " + unitprice;
        return (Iterable<Purchaseorderdetail>) entityManager.createQuery(psql).getResultList();
    }
}
