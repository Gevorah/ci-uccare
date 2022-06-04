package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;

public interface PurchaseorderdetailDelegate {
    public void savePurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
	public void editPurchaseorderdetail(Purchaseorderdetail purchaseorderdetail);
	public Optional<Purchaseorderdetail> findById(PurchaseorderdetailPK id);
	public Iterable<Purchaseorderdetail> findAll();
	public void delete(Purchaseorderdetail purchaseorderdetail);
}
