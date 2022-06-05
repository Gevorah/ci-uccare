package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderdetailService;

@RestController
public class PurchaseorderdetailRestControllerImp implements PurchaseorderdetailRestController {
    
    @Autowired
    PurchaseorderdetailService purchaseorderdetailservice;
    
    @RequestMapping(path="/api/purchaseorderdetails", method=RequestMethod.POST)
    public void savePurchaseorderdetail(@RequestBody Purchaseorderdetail purchaseorderdetail) {
        purchaseorderdetailservice.savePurchaseorderdetail(purchaseorderdetail);
    }

    @RequestMapping(path="/api/purchaseorderdetails/{id}", method=RequestMethod.PUT, consumes="application/json")
    public void editPurchaseorderdetail(@RequestBody Purchaseorderdetail purchaseorderdetail) {
        purchaseorderdetailservice.editPurchaseorderdetail(purchaseorderdetail);
    }

    @RequestMapping(path="/api/purchaseorderdetails/{id}", method=RequestMethod.GET)
    public Purchaseorderdetail findById(@PathVariable("id") PurchaseorderdetailPK id) {
        return purchaseorderdetailservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
    }

    @RequestMapping(path="/api/purchaseorderdetails", method=RequestMethod.GET)
    public Iterable<Purchaseorderdetail> findAll() {
        return purchaseorderdetailservice.findAll();
    }

    @RequestMapping(path="/api/purchaseorderdetails/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable("id") PurchaseorderdetailPK id) {
        purchaseorderdetailservice.delete(purchaseorderdetailservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id)));
    }
}
