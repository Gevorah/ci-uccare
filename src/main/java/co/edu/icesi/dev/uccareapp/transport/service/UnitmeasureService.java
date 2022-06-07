package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Unitmeasure;

public interface UnitmeasureService {

	public boolean saveUnitmeasure(Unitmeasure unitmeasure);

	public Optional<Unitmeasure> findById(String id);

	public Iterable<Unitmeasure> findAll();

	public void delete(Unitmeasure unitmeasure);

}
