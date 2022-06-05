package co.edu.icesi.dev.uccareapp.transport.rest;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;

public interface PurchaseorderheaderRestController {
    public void savePurchaseorderheader(Purchaseorderheader purchaseorderheader);
	public void editPurchaseorderheader(Purchaseorderheader purchaseorderheader);
	public Purchaseorderheader findById(Integer id);
	public Iterable<Purchaseorderheader> findAll();
	public void delete(Integer id);
}
