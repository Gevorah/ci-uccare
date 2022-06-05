package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderheaderService;

@RestController
public class PurchaseorderheaderRestControllerImp implements PurchaseorderheaderRestController {
    
    @Autowired
    PurchaseorderheaderService purchaseorderheaderservice;

    @RequestMapping(path="/api/purchaseorderheaders", method=RequestMethod.POST)
    public void savePurchaseorderheader(@RequestBody Purchaseorderheader purchaseorderheader) {
        purchaseorderheaderservice.savePurchaseorderheader(purchaseorderheader);
    }

    @RequestMapping(path="/api/purchaseorderheaders/{id}", method=RequestMethod.PUT, consumes="application/json")
    public void editPurchaseorderheader(@RequestBody Purchaseorderheader purchaseorderheader) {
        purchaseorderheaderservice.editPurchaseorderheader(purchaseorderheader);
    }

    @RequestMapping(path="/api/purchaseorderheaders/{id}", method=RequestMethod.GET)
    public Purchaseorderheader findById(@PathVariable("id") Integer id) {
        return purchaseorderheaderservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
    }

    @RequestMapping(path="/api/purchaseorderheaders", method=RequestMethod.GET)
    public Iterable<Purchaseorderheader> findAll() {
        return purchaseorderheaderservice.findAll();
    }

    @RequestMapping(path="/api/purchaseorderheaders/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        purchaseorderheaderservice.delete(purchaseorderheaderservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id)));
    }
}
