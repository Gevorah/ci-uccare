package co.edu.icesi.dev.uccareapp.transport.service;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;

public interface PurchaseorderheaderService {
	public boolean savePurchaseorderheader(Purchaseorderheader purchaseorderheader);
	public boolean editPurchaseorderheader(Purchaseorderheader purchaseorderheader);
}
