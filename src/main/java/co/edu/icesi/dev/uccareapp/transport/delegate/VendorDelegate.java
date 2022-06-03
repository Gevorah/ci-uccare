package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;

public interface VendorDelegate {
    public void saveVendor(Vendor vendor);
	public void editVendor(Vendor vendor);
	public Optional<Vendor> findById(Integer id);
	public Iterable<Vendor> findAll();
	public void delete(Vendor vendor);
}