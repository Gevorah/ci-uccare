package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;
import co.edu.icesi.dev.uccareapp.transport.service.VendorService;

@RestController
public class VendorRestControllerImp implements VendorRestController {
    
    @Autowired
    VendorService vendorservice;

    @RequestMapping(path="/vendors", method=RequestMethod.POST)
    public void saveVendor(Vendor vendor) {
        vendorservice.saveVendor(vendor);
    }

    @RequestMapping(path="/vendors/{id}", method=RequestMethod.PUT, consumes="application/json")
    public void editVendor(Vendor vendor) {
        vendorservice.editVendor(vendor);
    }

    @RequestMapping(path="/vendors/{id}", method=RequestMethod.GET)
    public Vendor findById(Integer id) {
        return vendorservice.findById(id).orElseThrow();
    }

    @RequestMapping(path="/vendors", method=RequestMethod.GET)
    public Iterable<Vendor> findAll() {
        return vendorservice.findAll();
    }

    @RequestMapping(path="/vendors/{id}", method=RequestMethod.DELETE)
    public void delete(Vendor vendor) {
        vendorservice.delete(vendor);
    }
}
