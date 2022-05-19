package co.edu.icesi.dev.uccareapp.transport.dao;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;

@Repository
@Transactional
public class PurchaseorderdetailDAOImp implements PurchaseorderdetailDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(*) FROM Purchaseorderdetail", Long.class).getSingleResult();
    }

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
        String jpql = "SELECT d FROM Purchaseorderdetail d";
        return entityManager.createQuery(jpql, Purchaseorderdetail.class).getResultList();
    }

    @Override
    public Iterable<Purchaseorderdetail> findByProductid(Integer productid) {
        String jpql = "SELECT d FROM Purchaseorderdetail d WHERE d.productid = :productid";
        TypedQuery<Purchaseorderdetail> query = entityManager.createQuery(jpql, Purchaseorderdetail.class);
        query.setParameter("productid", productid);
        return query.getResultList();
    }

    @Override
    public Iterable<Purchaseorderdetail> findByUnitprice(BigDecimal unitprice) {
        String jpql = "SELECT d FROM Purchaseorderdetail d WHERE d.unitprice = :unitprice";
        TypedQuery<Purchaseorderdetail> query = entityManager.createQuery(jpql, Purchaseorderdetail.class);
        query.setParameter("unitprice", unitprice);
        return query.getResultList();
    }
}
