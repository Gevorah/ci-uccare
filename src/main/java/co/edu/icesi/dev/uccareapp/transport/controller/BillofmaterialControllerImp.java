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

import co.edu.icesi.dev.uccareapp.transport.model.prod.Billofmaterial;
import co.edu.icesi.dev.uccareapp.transport.service.BillofmaterialService;

@Controller
public class BillofmaterialControllerImp implements BillofmaterialController {
    
    @Autowired
    BillofmaterialService billofmaterialService;

    @GetMapping("/billofmaterial")
    public String index(Model model) {
        model.addAttribute("billofmaterials", billofmaterialService.findAll());
        return "billofmaterial/index";
    }

    @GetMapping("/billofmaterial/add")
    public String showSaveForm(Model model) {
        model.addAttribute("billofmaterial", new Billofmaterial());
        return "billofmaterial/add-billofmaterial";
    }

    @PostMapping("/billofmaterial/add") 
    public String saveBillofmaterial(@Validated @ModelAttribute Billofmaterial billofmaterial, BindingResult bindingResult, 
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "billofmaterial/add-billofmaterial";
            }
            
            billofmaterialService.saveBillofmaterial(billofmaterial);
        }
        return "redirect:/billofmaterial/";
    }

    @GetMapping("/billofmaterial/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Billofmaterial> billofmaterialop = billofmaterialService.findById(id);
        if (billofmaterialop == null)
            throw new IllegalArgumentException("Invalid billofmaterial Id:" + id);
        model.addAttribute("purchaseorderdetail", billofmaterialop.get());
        return "billofmaterial/update-billofmaterial";
    }

    @PostMapping("/billofmaterial/edit/{id}")
    public String updateBillofmaterial(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, 
            @Validated @ModelAttribute Billofmaterial billofmaterial, BindingResult bindingResult, Model model ) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "billofmaterial/update-billofmaterial";
            }
            billofmaterialService.editBillofmaterial(billofmaterial);
        }
        return "redirect:/billofmaterial";
    }

    @GetMapping("/billofmaterial/del/{id}")
    public String deleteShipmethod(@PathVariable("id") Integer id, Model model) {
        Billofmaterial billofmaterial = billofmaterialService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid billofmaterial Id:" + id));
        billofmaterialService.delete(billofmaterial);
        return "redirect:/billofmaterial";
    }
}
