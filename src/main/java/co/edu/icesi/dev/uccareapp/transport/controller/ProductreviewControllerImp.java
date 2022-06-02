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

import co.edu.icesi.dev.uccareapp.transport.model.prod.Productreview;
import co.edu.icesi.dev.uccareapp.transport.service.ProductreviewService;

@Controller
public class ProductreviewControllerImp implements ProductreviewController {
    
    @Autowired
    ProductreviewService productreviewservice;

    @GetMapping("/productreview")
    public String index(Model model) {
        model.addAttribute("productreviews", productreviewservice.findAll());
        return "productreview/index";
    }

    @GetMapping("/productreview/add")
    public String showSaveForm(Model model) {
        model.addAttribute("productreview", new Productreview());
        return "productreview/add-productreview";
    }

    @PostMapping("/productreview/add") 
    public String saveProductreview(@Validated @ModelAttribute Productreview productreview, BindingResult bindingResult, 
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "productreview/add-productreview";
            }
            
            productreviewservice.saveProductreview(productreview);
        }
        return "redirect:/productreview/";
    }

    @GetMapping("/billofmaterial/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Productreview> productreviewop = productreviewservice.findById(id);
        if (productreviewop == null)
            throw new IllegalArgumentException("Invalid productreview Id:" + id);
        model.addAttribute("productreview", productreviewop.get());
        return "productreview/update-productreview";
    }

    @PostMapping("/billofmaterial/edit/{id}")
    public String updateProductreview(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, 
            @Validated @ModelAttribute Productreview productreview, BindingResult bindingResult, Model model ) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "productreview/update-productreview";
            }
            productreviewservice.editProductreview(productreview);
        }
        return "redirect:/productreview";
    }

    @GetMapping("/productreview/del/{id}")
    public String deleteShipmethod(@PathVariable("id") Integer id, Model model) {
        Productreview productreview = productreviewservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Productreview Id:" + id));
        productreviewservice.delete(productreview);
        return "redirect:/productreview";
    }
}
