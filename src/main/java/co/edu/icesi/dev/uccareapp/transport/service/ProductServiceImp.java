package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.ProductDAO;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;

@Service
public class ProductServiceImp implements ProductService {
    
    @Autowired
    private ProductDAO productDAO;

    @Override
    public boolean saveProduct(Product product) {
         if (product == null || productDAO
                .existsById(product.getProductid()))
            throw new NullPointerException("Product is null or already exists");

            productDAO.save(product);

            return true;
    }

    @Override
    public boolean editProduct(Product product) {
        if (product == null || productDAO
            .existsById(product.getProductid()))
        throw new NullPointerException("product is null or already exists");

        Product editproduct= productDAO.findById(product.getProductid()).get();
        editproduct.setClass_(product.getClass_());
        editproduct.setColor(product.getColor());
        editproduct.setDaystomanufacture(product.getDaystomanufacture());
        editproduct.setDiscontinueddate(product.getDiscontinueddate());
        editproduct.setFinishedgoodsflag(product.getFinishedgoodsflag());
        editproduct.setMakeflag(product.getMakeflag());
        editproduct.setModifieddate(product.getModifieddate());
        editproduct.setName(product.getName());
        editproduct.setProductid(product.getProductid());
        editproduct.setProductline(product.getProductline());
        editproduct.setProductnumber(product.getProductnumber());
        editproduct.setReorderpoint(product.getReorderpoint());
        editproduct.setRowguid(product.getRowguid());
        editproduct.setSafetystocklevel(product.getSafetystocklevel());
        editproduct.setSellenddate(product.getSellenddate());
        editproduct.setSellstartdate(product.getSellstartdate());
        editproduct.setSize(product.getSize());
        editproduct.setStandardcost(product.getStandardcost());
        editproduct.setStyle(product.getStyle());
        editproduct.setWorkorders(product.getWorkorders());
    
        productDAO.update(editproduct);
        return true;
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productDAO.findById(id);
    }

    @Override
    public Iterable<Product> findAll() {
        return productDAO.findAll().iterator().hasNext()?
        productDAO.findAll() : null;
    }

    @Override
    public void delete(Product product) {
        productDAO.delete(product);
        
    }
}
