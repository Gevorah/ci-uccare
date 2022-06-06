package co.edu.icesi.dev.uccareapp.transport.rest;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;

public interface PurchaseorderdetailRestController {
    public void savePurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
	public void editPurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
	public Purchaseorderdetail findById(Integer headerid, Integer detailid);
	public Iterable<Purchaseorderdetail> findAll();
	public void delete(Integer headerid, Integer detailid);
}
