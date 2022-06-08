package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Unitmeasure;

public interface UnitmeasureDAO {
	   
		long count();
	    void save(Unitmeasure entity);
	    void update(Unitmeasure entity);
	    void delete(Unitmeasure entity);
	    Optional<Unitmeasure> findById(String id);
	    boolean existsById(String id);
	    Iterable<Unitmeasure> findAll();
}
