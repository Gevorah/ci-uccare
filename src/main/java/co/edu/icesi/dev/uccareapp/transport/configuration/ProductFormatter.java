package co.edu.icesi.dev.uccareapp.transport.configuration;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;
import co.edu.icesi.dev.uccareapp.transport.service.ProductService;

@Service
public class ProductFormatter implements Formatter<Product> {

    @Autowired
    ProductService productservice;

    @Override
    public String print(Product object, Locale locale) {
        return (object != null ? object.getProductid().toString() : "");
    }

    @Override
    public Product parse(String text, Locale locale) throws ParseException {
        Integer id = Integer.valueOf(text);
        return this.productservice.findById(id).orElseThrow();
    }
}
