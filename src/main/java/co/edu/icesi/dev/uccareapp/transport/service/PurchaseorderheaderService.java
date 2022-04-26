package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;

public interface PurchaseorderheaderService {
	public boolean savePurchaseorderheader(Purchaseorderheader purchaseorderheader);
	public boolean editPurchaseorderheader(Purchaseorderheader purchaseorderheader);
	public Optional<Purchaseorderheader> findById(Integer id);
	public Iterable<Purchaseorderheader> findAll();
	public void delete(Purchaseorderheader purchaseorderheader);
}
