package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;

@Component
public class VendorDelegateImp implements VendorDelegate {

    private final String URI = "http://localhost:8080/vendors/";

    @Autowired
    private RestTemplate resttemplate;

    public void saveVendor(Vendor vendor) {
        resttemplate.postForObject(URI, vendor, Vendor.class);
    }
    
    public void editVendor(Vendor vendor) {
        resttemplate.put(URI, vendor);
    }

    public Optional<Vendor> findById(Integer id) {
        return Optional.ofNullable(resttemplate.getForObject(URI + id, Vendor.class));
    }

    public Iterable<Vendor> findAll() {
        Vendor[] array = resttemplate.getForObject(URI, Vendor[].class);

        Iterable<Vendor> vendors = () -> new Iterator<Vendor>() {
            private int index = 0;
            @Override public boolean hasNext() { return array.length > index; }
            @Override public Vendor next() { return array[index++]; }
        };

        return vendors;
    }

    public void delete(Vendor vendor) {
        resttemplate.delete(URI + vendor.getBusinessentityid());
    }
}