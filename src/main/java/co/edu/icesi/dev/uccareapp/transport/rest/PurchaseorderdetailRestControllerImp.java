package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderdetailService;

@Controller
public class PurchaseorderdetailRestControllerImp implements PurchaseorderdetailRestController {
    
    @Autowired
    PurchaseorderdetailService purchaseorderdetailservice;
    
    @RequestMapping(path="/purchaseorderdetails", method=RequestMethod.POST)
    public void savePurchaseorderdetail(Purchaseorderdetail purchaseorderdetail) {
        purchaseorderdetailservice.savePurchaseorderdetail(purchaseorderdetail);
    }

    @RequestMapping(path="/purchaseorderdetails/{id}", method=RequestMethod.PUT, consumes="application/json")
    public void editPurchaseorderdetail(Purchaseorderdetail purchaseorderdetail) {
        purchaseorderdetailservice.editPurchaseorderdetail(purchaseorderdetail);
    }

    @RequestMapping(path="/purchaseorderdetails/{id}", method=RequestMethod.GET)
    public Purchaseorderdetail findById(PurchaseorderdetailPK id) {
        return purchaseorderdetailservice.findById(id).orElseThrow();
    }

    @RequestMapping(path="/purchaseorderdetails", method=RequestMethod.GET)
    public Iterable<Purchaseorderdetail> findAll() {
        return purchaseorderdetailservice.findAll();
    }

    @RequestMapping(path="/purchaseorderdetails/{id}", method=RequestMethod.DELETE)
    public void delete(Purchaseorderdetail purchaseorderdetail) {
        purchaseorderdetailservice.delete(purchaseorderdetail);
    }
}
