package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;
import co.edu.icesi.dev.uccareapp.transport.service.VendorService;

@RestController
public class VendorRestControllerImp implements VendorRestController {
    
    @Autowired
    VendorService vendorservice;

    @RequestMapping(path="/api/vendors", method=RequestMethod.POST)
    public void saveVendor(@RequestBody Vendor vendor) {
        vendorservice.saveVendor(vendor);
    }

    @RequestMapping(path="/api/vendors/{id}", method=RequestMethod.PUT, consumes="application/json")
    public void editVendor(@RequestBody Vendor vendor) {
        vendorservice.editVendor(vendor);
    }

    @RequestMapping(path="/api/vendors/{id}", method=RequestMethod.GET)
    public Vendor findById(@PathVariable("id") Integer id) {
        return vendorservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
    }

    @RequestMapping(path="/api/vendors", method=RequestMethod.GET)
    public Iterable<Vendor> findAll() {
        return vendorservice.findAll();
    }

    @RequestMapping(path="/api/vendors/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        vendorservice.delete(vendorservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id)));
    }
}
