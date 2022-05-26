package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Productreview;

public interface ProductreviewService {
    
    public boolean saveProductreview(Productreview productreview);
	public boolean editProductreview(Productreview productreview);
	public Optional<Productreview> findById(Integer id);
	public Iterable<Productreview> findAll();
	public void delete(Productreview productreview);
}
