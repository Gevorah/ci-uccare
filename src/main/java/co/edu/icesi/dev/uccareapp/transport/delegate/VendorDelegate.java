package co.edu.icesi.dev.uccareapp.transport.delegate;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;

public interface VendorDelegate {
    public Vendor saveVendor(Vendor vendor);
	public void editVendor(Vendor vendor);
	public Vendor findById(Integer id);
	public Iterable<Vendor> findAll();
	public void delete(Vendor vendor);
}