package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;

public interface VendorService {
	public boolean saveVendor(Vendor vendor);
	public boolean editVendor(Vendor vendor);
	public Optional<Vendor> findById(Integer id);
	public Iterable<Vendor> findAll();
	public void delete(Vendor vendor);
}
