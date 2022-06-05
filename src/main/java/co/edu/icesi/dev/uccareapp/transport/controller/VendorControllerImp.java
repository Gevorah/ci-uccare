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

import co.edu.icesi.dev.uccareapp.transport.delegate.VendorDelegate;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;
import co.edu.icesi.dev.uccareapp.transport.repository.BusinessentityRepository;

@Controller
public class VendorControllerImp implements VendorController {
    
    @Autowired
    VendorDelegate vendordelegate;

    @Autowired
    BusinessentityRepository businessentityrepository;

    @GetMapping("/vendors")
    public String index(Model model) {
        model.addAttribute("vendors", vendordelegate.findAll());
        return "vendors/index";
    }

    @GetMapping("/vendors/add")
    public String showSaveForm(Model model) {
        model.addAttribute("vendor", new Vendor());
        model.addAttribute("businessentities", businessentityrepository.findAll());
        return "vendors/add-vendor";
    }

    @PostMapping("/vendors/add") 
    public String saveVendor(@Validated @ModelAttribute Vendor vendor, BindingResult bindingResult, 
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "vendors/add-vendor";
            }
            vendordelegate.saveVendor(vendor);
        }
        return "redirect:/vendors";
    }

    @GetMapping("/vendors/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Vendor vendor = vendordelegate.findById(id);
        model.addAttribute("vendor", vendor);
        model.addAttribute("businessentities", businessentityrepository.findAll());
        return "vendors/update-vendor";
    }

    @PostMapping("/vendors/edit/{id}")
    public String updateVendor(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, 
            @Validated @ModelAttribute Vendor vendor, BindingResult bindingResult, Model model ) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "vendors/update-vendor";
            }
            vendordelegate.editVendor(vendor);
        }
        return "redirect:/vendors";
    }

    @GetMapping("/vendors/del/{id}")
    public String deleteVendor(@PathVariable("id") Integer id, Model model) {
        vendordelegate.delete(vendordelegate.findById(id));
        return "redirect:/vendors";
    }
}
