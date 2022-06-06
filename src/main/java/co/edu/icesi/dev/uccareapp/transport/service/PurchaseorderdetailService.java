package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;

public interface PurchaseorderdetailService {
	public boolean savePurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
	public boolean editPurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
	public Optional<Purchaseorderdetail> findById(Integer headerid, Integer detailid);
	public Iterable<Purchaseorderdetail> findAll();
	public void delete(Purchaseorderdetail purchaseorderdetail);
}
