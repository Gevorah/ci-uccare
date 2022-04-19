package co.edu.icesi.dev.uccareapp.transport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;

@Repository
public interface PurchaseorderheaderRepository extends CrudRepository<Purchaseorderheader, Integer> {

}
