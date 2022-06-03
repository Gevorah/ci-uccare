package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;

public interface ProductService {
    
    public boolean saveProduct(Product product);
	public boolean editProduct(Product product);
	public Optional<Product> findById(Integer id);
	public Iterable<Product> findAll();
	public void delete(Product product);
}
