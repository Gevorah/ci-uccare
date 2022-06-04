package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;

@Component
public class ShipmethodDelegateImp implements ShipmethodDelegate {

    private final String URI = "http://localhost:8080/shipmethods/";

    @Autowired
    private RestTemplate resttemplate;

    public void saveShipmethod(Shipmethod shipmethod) {
        resttemplate.postForObject(URI, shipmethod, Shipmethod.class);
    }
    
    public void editShipmethod(Shipmethod shipmethod) {
        resttemplate.put(URI, shipmethod);
    }

    public Optional<Shipmethod> findById(Integer id) {
        return Optional.ofNullable(resttemplate.getForObject(URI + id, Shipmethod.class));
    }

    public Iterable<Shipmethod> findAll() {
        Shipmethod[] array = resttemplate.getForObject(URI, Shipmethod[].class);

        Iterable<Shipmethod> vendors = () -> new Iterator<Shipmethod>() {
            private int index = 0;
            @Override public boolean hasNext() { return array.length > index; }
            @Override public Shipmethod next() { return array[index++]; }
        };

        return vendors;
    }

    public void delete(Shipmethod shipmethod) {
        resttemplate.delete(URI + shipmethod.getShipmethodid());
    }
}