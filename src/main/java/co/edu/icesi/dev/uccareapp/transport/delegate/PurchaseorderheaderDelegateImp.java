package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.time.LocalDate;
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
        resttemplate.put(URI + purchaseorderheader.getPurchaseorderid(), purchaseorderheader);
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

    public Iterable<Object[]> findByDateRange(LocalDate start, LocalDate end) {
        Object[][] matrix = resttemplate.getForObject(URI + "queries/query=range" + start + "+" + end, Object[][].class);

        Iterable<Object[]> purchaseorderheaders = () -> new Iterator<Object[]>() {
            private int index = 0;
            @Override public boolean hasNext() { return matrix.length > index; }
            @Override public Object[] next() { return matrix[index++]; }
        };

        return purchaseorderheaders;
    }

    public Iterable<Purchaseorderheader> findByTwoDetails() {
        Purchaseorderheader[] array = resttemplate.getForObject(URI + "queries/query=two", Purchaseorderheader[].class);

        Iterable<Purchaseorderheader> purchaseorderheaders = () -> new Iterator<Purchaseorderheader>() {
            private int index = 0;
            @Override public boolean hasNext() { return array.length > index; }
            @Override public Purchaseorderheader next() { return array[index++]; }
        };

        return purchaseorderheaders;
    }
}