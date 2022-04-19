package co.edu.icesi.dev.uccareapp.transport.service;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;

public interface VendorService {
	public boolean saveVendor(Vendor vendor);
	public boolean editVendor(Vendor vendor);
}
