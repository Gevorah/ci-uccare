package co.edu.icesi.dev.uccareapp.transport.delegate;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;

public interface PurchaseorderdetailDelegate {
    public Purchaseorderdetail savePurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
	public void editPurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
	public Purchaseorderdetail findById(Integer headerid, Integer detailid);
	public Iterable<Purchaseorderdetail> findAll();
	public void delete(Purchaseorderdetail purchaseorderdetail);
}
