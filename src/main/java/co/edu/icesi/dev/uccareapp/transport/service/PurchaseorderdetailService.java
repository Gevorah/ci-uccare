package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;

public interface PurchaseorderdetailService {
	public boolean savePurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
	public boolean editPurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
	public Optional<Purchaseorderdetail> findById(PurchaseorderdetailPK id);
	public Iterable<Purchaseorderdetail> findAll();
	public void delete(Purchaseorderdetail purchaseorderdetail);
}
