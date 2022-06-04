package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderheaderService;

@Controller
public class PurchaseorderheaderRestControllerImp implements PurchaseorderheaderRestController {
    
    @Autowired
    PurchaseorderheaderService purchaseorderheaderservice;

    @RequestMapping(path="/purchaseorderheaders", method=RequestMethod.POST)
    public void savePurchaseorderheader(Purchaseorderheader purchaseorderheader) {
        purchaseorderheaderservice.savePurchaseorderheader(purchaseorderheader);
    }

    @RequestMapping(path="/purchaseorderheaders/{id}", method=RequestMethod.PUT, consumes="application/json")
    public void editPurchaseorderheader(Purchaseorderheader purchaseorderheader) {
        purchaseorderheaderservice.editPurchaseorderheader(purchaseorderheader);
    }

    @RequestMapping(path="/purchaseorderheaders/{id}", method=RequestMethod.GET)
    public Purchaseorderheader findById(Integer id) {
        return purchaseorderheaderservice.findById(id).orElseThrow();
    }

    @RequestMapping(path="/purchaseorderheaders", method=RequestMethod.GET)
    public Iterable<Purchaseorderheader> findAll() {
        return purchaseorderheaderservice.findAll();
    }

    @RequestMapping(path="/purchaseorderheaders/{id}", method=RequestMethod.DELETE)
    public void delete(Purchaseorderheader purchaseorderheader) {
        purchaseorderheaderservice.delete(purchaseorderheader);
    }
}
