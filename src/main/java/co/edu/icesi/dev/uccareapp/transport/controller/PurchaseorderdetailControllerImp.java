package co.edu.icesi.dev.uccareapp.transport.controller;

import java.util.Optional;

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

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;
import co.edu.icesi.dev.uccareapp.transport.repository.PurchaseorderheaderRepository;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderdetailService;

@Controller
public class PurchaseorderdetailControllerImp implements PurchaseorderdetailController {
    
    @Autowired
    PurchaseorderdetailService purchaseorderdetailservice;
    
    @Autowired
    PurchaseorderheaderRepository purchaseorderheaderrepository;

    @GetMapping("/purchaseorderdetails")
    public String index(Model model) {
        model.addAttribute("purchaseorderdetails", purchaseorderdetailservice.findAll());
        return "purchaseorderdetails/index";
    }

    @GetMapping("/purchaseorderdetails/add")
    public String showSaveForm(Model model) {
        model.addAttribute("purchaseorderdetail", new Purchaseorderdetail());
        model.addAttribute("purchaseorderheaders", purchaseorderheaderrepository.findAll());
        model.addAttribute("pk", new PurchaseorderdetailPK());
        return "purchaseorderdetails/add-purchaseorderdetail";
    }

    @PostMapping("/purchaseorderdetails/add") 
    public String saveShipmethod(@Validated @ModelAttribute Purchaseorderdetail purchaseorderdetail, BindingResult bindingResult, 
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "purchaseorderdetails/add-purchaseorderdetail";
            }
            purchaseorderdetailservice.savePurchaseorderdetail(purchaseorderdetail);
        }
        return "redirect:/purchaseorderdetails/";
    }

    @GetMapping("/purchaseorderdetails/edit/{id}")
    public String showUpdateForm(@PathVariable("id") PurchaseorderdetailPK id, Model model) {
        Optional<Purchaseorderdetail> purchaseorderdetail = purchaseorderdetailservice.findById(id);
        if (purchaseorderdetail == null)
            throw new IllegalArgumentException("Invalid purchaseorderdetail Id:" + id);
        model.addAttribute("purchaseorderdetail", purchaseorderdetail.get());
        model.addAttribute("purchaseorderheaders", purchaseorderheaderrepository.findAll());
        return "purchaseorderdetails/update-purchaseorderdetail";
    }

    @PostMapping("/purchaseorderdetails/edit/{id}")
    public String updateShipmethod(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, 
            @Validated @ModelAttribute Purchaseorderdetail purchaseorderdetail, BindingResult bindingResult, Model model ) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "purchaseorderdetails/add-purchaseorderdetail";
            }
            purchaseorderdetailservice.editPurchaseorderdetail(purchaseorderdetail);
        }
        return "redirect:/purchaseorderdetails";
    }

    @GetMapping("/purchaseorderdetails/del/{id}")
    public String deleteShipmethod(@PathVariable("id") PurchaseorderdetailPK id, Model model) {
        Purchaseorderdetail purchaseorderdetail = purchaseorderdetailservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid purchaseorderdetails Id:" + id));
        purchaseorderdetailservice.delete(purchaseorderdetail);
        return "redirect:/purchaseorderdetails";
    }
}
