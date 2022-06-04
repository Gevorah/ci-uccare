package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;
import co.edu.icesi.dev.uccareapp.transport.service.ShipmethodService;

@Controller
public class ShipmethodRestControllerImp implements ShipmethodRestController {
    
    @Autowired
    ShipmethodService shipmethodservice;

    @RequestMapping(path="/shipmethods", method=RequestMethod.POST)
    public void saveShipmethod(Shipmethod shipmethod) {
        shipmethodservice.saveShipmethod(shipmethod);
    }

    @RequestMapping(path="/shipmethods/{id}", method=RequestMethod.PUT, consumes="application/json")
    public void editShipmethod(Shipmethod shipmethod) {
        shipmethodservice.editShipmethod(shipmethod);
    }

    @RequestMapping(path="/shipmethods/{id}", method=RequestMethod.GET)
    public Shipmethod findById(Integer id) {
        return shipmethodservice.findById(id).orElseThrow();
    }

    @RequestMapping(path="/shipmethods", method=RequestMethod.GET)
    public Iterable<Shipmethod> findAll() {
        return shipmethodservice.findAll();
    }

    @RequestMapping(path="/shipmethods/{id}", method=RequestMethod.DELETE)
    public void delete(Shipmethod shipmethod) {
        shipmethodservice.delete(shipmethod);
    }
}
