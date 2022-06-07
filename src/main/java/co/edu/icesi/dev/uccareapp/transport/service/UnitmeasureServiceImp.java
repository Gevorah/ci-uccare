package co.edu.icesi.dev.uccareapp.transport.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.UnitmeasureDAO;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Unitmeasure;

@Service
public class UnitmeasureServiceImp implements UnitmeasureService {

	 @Autowired
	    private UnitmeasureDAO unitmeauseredao;

	    @Override
	    public boolean saveUnitmeasure(Unitmeasure unitmeasure) {
	         if (unitmeasure == null || unitmeauseredao
	                .existsById(unitmeasure.getUnitmeasurecode()))
	            throw new NullPointerException("Unitmeasure is null or already exists");

	         unitmeauseredao.save(unitmeasure);

	            return true;
	    }

	    @Override
	    public Optional<Unitmeasure> findById(String id) {
	        return unitmeauseredao.findById(id);
	    }

	    @Override
	    public Iterable<Unitmeasure> findAll() {
	        return unitmeauseredao.findAll().iterator().hasNext()?
	        		unitmeauseredao.findAll() : null;
	    }

	    @Override
	    public void delete(Unitmeasure unitmeasure) {
	    	unitmeauseredao.delete(unitmeasure);
	        
	    }
	    
}
