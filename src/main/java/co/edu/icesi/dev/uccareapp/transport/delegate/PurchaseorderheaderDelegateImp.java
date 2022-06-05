package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;

@Component
public class PurchaseorderheaderDelegateImp implements PurchaseorderheaderDelegate {

    private final String URI = "http://localhost:8080/api/purchaseorderheaders/";

    @Autowired
    private RestTemplate resttemplate;

    public void savePurchaseorderheader(Purchaseorderheader purchaseorderheader) {
        resttemplate.postForObject(URI, purchaseorderheader, Purchaseorderheader.class);
    }
    
    public void editPurchaseorderheader(Purchaseorderheader purchaseorderheader) {
        resttemplate.put(URI, purchaseorderheader);
    }

    public Purchaseorderheader findById(Integer id) {
        return resttemplate.getForObject(URI + id, Purchaseorderheader.class);
    }

    public Iterable<Purchaseorderheader> findAll() {
        Purchaseorderheader[] array = resttemplate.getForObject(URI, Purchaseorderheader[].class);

        Iterable<Purchaseorderheader> purchaseorderheaders = () -> new Iterator<Purchaseorderheader>() {
            private int index = 0;
            @Override public boolean hasNext() { return array.length > index; }
            @Override public Purchaseorderheader next() { return array[index++]; }
        };

        return purchaseorderheaders;
    }

    public void delete(Purchaseorderheader purchaseorderheader) {
        resttemplate.delete(URI + purchaseorderheader.getPurchaseorderid());
    }
}