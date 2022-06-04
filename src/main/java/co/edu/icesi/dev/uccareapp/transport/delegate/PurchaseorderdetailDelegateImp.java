package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;

@Component
public class PurchaseorderdetailDelegateImp implements PurchaseorderdetailDelegate {

    private final String URI = "http://localhost:8080/purchaseorderdetails/";

    @Autowired
    private RestTemplate resttemplate;

    public void savePurchaseorderdetail(Purchaseorderdetail purchaseorderdetail) {
        resttemplate.postForObject(URI, purchaseorderdetail, Purchaseorderdetail.class);
    }
    
    public void editPurchaseorderdetail(Purchaseorderdetail purchaseorderdetail) {
        resttemplate.put(URI, purchaseorderdetail);
    }

    public Purchaseorderdetail findById(PurchaseorderdetailPK id) {
        return resttemplate.getForObject(URI + id, Purchaseorderdetail.class);
    }

    public Iterable<Purchaseorderdetail> findAll() {
        Purchaseorderdetail[] array = resttemplate.getForObject(URI, Purchaseorderdetail[].class);

        Iterable<Purchaseorderdetail> purchaseorderdetails = () -> new Iterator<Purchaseorderdetail>() {
            private int index = 0;
            @Override public boolean hasNext() { return array.length > index; }
            @Override public Purchaseorderdetail next() { return array[index++]; }
        };

        return purchaseorderdetails;
    }

    public void delete(Purchaseorderdetail purchaseorderdetail) {
        resttemplate.delete(URI + purchaseorderdetail.getId());
    }
}