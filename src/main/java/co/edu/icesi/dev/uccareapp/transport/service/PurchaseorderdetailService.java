package co.edu.icesi.dev.uccareapp.transport.service;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;

public interface PurchaseorderdetailService {
	public boolean savePurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
	public boolean editPurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
}
