package co.edu.icesi.dev.uccareapp.transport.rest;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;

public interface VendorRestController {
    public void saveVendor(Vendor vendor);
	public void editVendor(Vendor vendor);
	public Vendor findById(Integer id);
	public Iterable<Vendor> findAll();
	public void delete(Vendor vendor);
}
