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
import co.edu.icesi.dev.uccareapp.transport.service.ProductService;
import co.edu.icesi.dev.uccareapp.transport.service.ProductreviewService;

@Controller
public class ProductreviewControllerImp implements ProductreviewController {
    
    @Autowired
    ProductreviewService productreviewservice;

    @Autowired
    ProductService productservice;

    @GetMapping("/productreviews")
    public String index(Model model) {
        model.addAttribute("productreviews", productreviewservice.findAll());
        return "productreviews/index";
    }

    @GetMapping("/productreviews/add")
    public String showSaveForm(Model model) {
        model.addAttribute("productreview", new Productreview());
        model.addAttribute("products", productservice.findAll() );
        return "productreviews/add-productreview";
    }

    @PostMapping("/productreviews/add") 
    public String saveProductreview(@Validated @ModelAttribute Productreview productreview, BindingResult bindingResult, 
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "productreviews/add-productreview";
            }
            
            productreviewservice.saveProductreview(productreview);
        }
        return "redirect:/productreviews/";
    }

    @GetMapping("/productreviews/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Productreview> productreviewop = productreviewservice.findById(id);
        if (productreviewop == null)
            throw new IllegalArgumentException("Invalid productreview Id:" + id);
        model.addAttribute("productreview", productreviewop.get());
        model.addAttribute("products", productservice.findAll() );
        return "productreviews/update-productreview";
    }

    @PostMapping("/productreviews/edit/{id}")
    public String updateProductreview(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, 
            @Validated @ModelAttribute Productreview productreview, BindingResult bindingResult, Model model ) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "productreviews/update-productreview";
            }
            productreviewservice.editProductreview(productreview);
        }
        return "redirect:/productreviews";
    }

    @GetMapping("/productreviews/del/{id}")
    public String deleteShipmethod(@PathVariable("id") Integer id, Model model) {
        Productreview productreview = productreviewservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Productreview Id:" + id));
        productreviewservice.delete(productreview);
        return "redirect:/productreviews";
    }
}
