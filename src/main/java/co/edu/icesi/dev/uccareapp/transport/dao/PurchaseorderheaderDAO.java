package co.edu.icesi.dev.uccareapp.transport.dao;

import java.time.LocalDate;
import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;

public interface PurchaseorderheaderDAO {
    
    long count();
    void save(Purchaseorderheader entity);
    void update(Purchaseorderheader entity);
    void delete(Purchaseorderheader entity);
    void deleteAll();
    Optional<Purchaseorderheader> findById(Integer id);
    boolean existsById(Integer id);
    Iterable<Purchaseorderheader> findAll();

    Iterable<Purchaseorderheader> findByShipmethodid(Integer shipmethodid);
    Iterable<Purchaseorderheader> findByVendorid(Integer vendorid);
    Iterable<Object[]> findByDateRange(LocalDate start, LocalDate end);
    Iterable<Purchaseorderheader> findByTwoDetails();
}
