package co.edu.icesi.dev.uccareapp.transport.dao;

import java.math.BigDecimal;
import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;

public interface PurchaseorderdetailDAO {

    long count();
    void save(Purchaseorderdetail entity);
    void update(Purchaseorderdetail entity);
    void delete(Purchaseorderdetail entity);
    void deleteAll();
    Optional<Purchaseorderdetail> findById(PurchaseorderdetailPK id);
    boolean existsById(PurchaseorderdetailPK id);
    Iterable<Purchaseorderdetail> findAll();

    Iterable<Purchaseorderdetail> findByProductid(Integer productid);
    Iterable<Purchaseorderdetail> findByUnitprice(BigDecimal unitprice);
}
