package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;

public interface PurchaseorderheaderDelegate {
    public void savePurchaseorderheader(Purchaseorderheader purchaseorderheader);
	public void editPurchaseorderheader(Purchaseorderheader purchaseorderheader);
	public Optional<Purchaseorderheader> findById(Integer id);
	public Iterable<Purchaseorderheader> findAll();
	public void delete(Purchaseorderheader purchaseorderheader);
}
