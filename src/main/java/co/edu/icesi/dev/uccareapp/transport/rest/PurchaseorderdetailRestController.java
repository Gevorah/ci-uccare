package co.edu.icesi.dev.uccareapp.transport.rest;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;

public interface PurchaseorderdetailRestController {
    public void savePurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
	public void editPurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
	public Purchaseorderdetail findById(PurchaseorderdetailPK id);
	public Iterable<Purchaseorderdetail> findAll();
	public void delete(Purchaseorderdetail purchaseorderdetail);
}
