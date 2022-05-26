package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Billofmaterial;

public interface BillofmaterialService {

    public boolean saveBillofmaterial(Billofmaterial billofmaterial);
	public boolean editBillofmaterial(Billofmaterial billofmaterial);
	public Optional<Billofmaterial> findById(Integer id);
	public Iterable<Billofmaterial> findAll();
	public void delete(Billofmaterial billofmaterial);
    
}
