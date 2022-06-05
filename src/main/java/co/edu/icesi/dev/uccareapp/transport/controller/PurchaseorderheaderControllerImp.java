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

import co.edu.icesi.dev.uccareapp.transport.delegate.PurchaseorderheaderDelegate;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.repository.EmployeeRepository;

@Controller
public class PurchaseorderheaderControllerImp implements PurchaseorderheaderController {
    
    @Autowired
    PurchaseorderheaderDelegate purchaseorderheaderdelegate;

    @Autowired
    EmployeeRepository employeerepository;

    @GetMapping("/purchaseorderheaders")
    public String index(Model model) {
        model.addAttribute("purchaseorderheaders", purchaseorderheaderdelegate.findAll());
        return "purchaseorderheaders/index";
    }

    @GetMapping("/purchaseorderheaders/add")
    public String showSaveForm(Model model) {
        model.addAttribute("purchaseorderheader", new Purchaseorderheader());
        model.addAttribute("employees", employeerepository.findAll());
        return "purchaseorderheaders/add-purchaseorderheader";
    }

    @PostMapping("/purchaseorderheaders/add") 
    public String saveShipmethod(@Validated @ModelAttribute Purchaseorderheader purchaseorderheader, BindingResult bindingResult, 
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "purchaseorderheaders/add-purchaseorderheader";
            }
            purchaseorderheaderdelegate.savePurchaseorderheader(purchaseorderheader);
        }
        return "redirect:/purchaseorderheaders/";
    }

    @GetMapping("/purchaseorderheaders/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("purchaseorderheader", purchaseorderheaderdelegate.findById(id));
        model.addAttribute("employees", employeerepository.findAll());
        return "purchaseorderheaders/update-purchaseorderheader";
    }

    @PostMapping("/purchaseorderheaders/edit/{id}")
    public String updateShipmethod(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, 
            @Validated @ModelAttribute Purchaseorderheader purchaseorderheader, BindingResult bindingResult, Model model ) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "purchaseorderheaders/update-purchaseorderheader";
            }
            purchaseorderheaderdelegate.editPurchaseorderheader(purchaseorderheader);
        }
        return "redirect:/purchaseorderheaders";
    }

    @GetMapping("/purchaseorderheaders/del/{id}")
    public String deleteShipmethod(@PathVariable("id") Integer id, Model model) {
        purchaseorderheaderdelegate.delete(purchaseorderheaderdelegate.findById(id));
        return "redirect:/purchaseorderheaders";
    }
}
