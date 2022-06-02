package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.ProductreviewDAO;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productreview;

@Service
public class ProductreviewServiceImp implements ProductreviewService{
    
    @Autowired
    private ProductreviewDAO productreviewDAO;

    @Override
    public boolean saveProductreview(Productreview productreview) {
         if (productreview == null || productreviewDAO
                .existsById(productreview.getProductreviewid()))
            throw new NullPointerException("Productreview is null or already exists");

            productreviewDAO.save(productreview);

            return true;
    }

    @Override
    public boolean editProductreview(Productreview productreview) {
        if (productreview == null || productreviewDAO
            .existsById(productreview.getProductreviewid()))
        throw new NullPointerException("Productreview is null or already exists");

        Productreview editProductreview= productreviewDAO.findById(productreview.getProductreviewid()).get();
        editProductreview.setComments(productreview.getComments());
        editProductreview.setEmailaddress(productreview.getEmailaddress());
        editProductreview.setModifieddate(productreview.getModifieddate());
        editProductreview.setProductreviewid(productreview.getProductreviewid());
        editProductreview.setRating(productreview.getRating());
        editProductreview.setReviewdate(productreview.getReviewdate());
        editProductreview.setReviewername(productreview.getReviewername());

        productreviewDAO.update(editProductreview);
        return true;
    }

    @Override
    public Optional<Productreview> findById(Integer id) {
        return productreviewDAO.findById(id);
    }

    @Override
    public Iterable<Productreview> findAll() {
        return productreviewDAO.findAll().iterator().hasNext()?
        productreviewDAO.findAll() : null;
    }

    @Override
    public void delete(Productreview productreview) {
        productreviewDAO.delete(productreview);
        
    }
}
