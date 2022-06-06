package co.edu.icesi.dev.uccareapp.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.dev.uccareapp.transport.delegate.PurchaseorderdetailDelegate;
import co.edu.icesi.dev.uccareapp.transport.delegate.PurchaseorderheaderDelegate;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;

@Controller
public class PurchaseorderdetailControllerImp implements PurchaseorderdetailController {
    
    @Autowired
    PurchaseorderdetailDelegate purchaseorderdetaildelegate;
    
    @Autowired
    PurchaseorderheaderDelegate purchaseorderheaderdelegate;

    @GetMapping("/purchaseorderdetails")
    public String index(Model model) {
        model.addAttribute("purchaseorderdetails", purchaseorderdetaildelegate.findAll());
        return "purchaseorderdetails/index";
    }

    @GetMapping("/purchaseorderdetails/add")
    public String showSaveForm(Model model) {
        model.addAttribute("purchaseorderdetail", new Purchaseorderdetail());
        model.addAttribute("purchaseorderheaders", purchaseorderheaderdelegate.findAll());
        model.addAttribute("pk", new PurchaseorderdetailPK());
        return "purchaseorderdetails/add-purchaseorderdetail";
    }

    @PostMapping("/purchaseorderdetails/add") 
    public String saveShipmethod(@Validated @ModelAttribute Purchaseorderdetail purchaseorderdetail, @ModelAttribute PurchaseorderdetailPK pk, BindingResult bindingResult, 
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "purchaseorderdetails/add-purchaseorderdetail";
            }
            purchaseorderdetail.setId(pk);
            purchaseorderdetaildelegate.savePurchaseorderdetail(purchaseorderdetail);
        }
        return "redirect:/purchaseorderdetails/";
    }

    @GetMapping("/purchaseorderdetails/edit/{headerid}-{detailid}")
    public String showUpdateForm(@PathVariable("headerid") Integer headerid, @PathVariable("detailid") Integer detailid, Model model) {
        model.addAttribute("purchaseorderdetail", purchaseorderdetaildelegate.findById(headerid, detailid));
        model.addAttribute("purchaseorderheaders", purchaseorderheaderdelegate.findAll());
        return "purchaseorderdetails/update-purchaseorderdetail";
    }

    @PostMapping("/purchaseorderdetails/edit/{headerid}-{detailid}")
    public String updateShipmethod(@PathVariable("headerid") Integer headerid, @PathVariable("detailid") Integer detailid, @RequestParam(value = "action", required = true) String action, 
            @Validated @ModelAttribute Purchaseorderdetail purchaseorderdetail, BindingResult bindingResult, Model model ) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "purchaseorderdetails/update-purchaseorderdetail";
            }
            purchaseorderdetaildelegate.editPurchaseorderdetail(purchaseorderdetail);
        }
        return "redirect:/purchaseorderdetails";
    }

    @GetMapping("/purchaseorderdetails/del/{headerid}-{detailid}")
    public String deleteShipmethod(@PathVariable("headerid") Integer headerid, @PathVariable("detailid") Integer detailid, Model model) {
        purchaseorderdetaildelegate.delete(purchaseorderdetaildelegate.findById(headerid, detailid));
        return "redirect:/purchaseorderdetails";
    }
}