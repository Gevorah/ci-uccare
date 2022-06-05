package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;
import co.edu.icesi.dev.uccareapp.transport.service.ShipmethodService;

@RestController
public class ShipmethodRestControllerImp implements ShipmethodRestController {
    
    @Autowired
    ShipmethodService shipmethodservice;

    @RequestMapping(path="/api/shipmethods", method=RequestMethod.POST)
    public void saveShipmethod(@RequestBody Shipmethod shipmethod) {
        shipmethodservice.saveShipmethod(shipmethod);
    }

    @RequestMapping(path="/api/shipmethods/{id}", method=RequestMethod.PUT, consumes="application/json")
    public void editShipmethod(@RequestBody Shipmethod shipmethod) {
        shipmethodservice.editShipmethod(shipmethod);
    }

    @RequestMapping(path="/api/shipmethods/{id}", method=RequestMethod.GET)
    public Shipmethod findById(@PathVariable("id") Integer id) {
        return shipmethodservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
    }

    @RequestMapping(path="/api/shipmethods", method=RequestMethod.GET)
    public Iterable<Shipmethod> findAll() {
        return shipmethodservice.findAll();
    }

    @RequestMapping(path="/api/shipmethods/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        shipmethodservice.delete(shipmethodservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id)));
    }
}
