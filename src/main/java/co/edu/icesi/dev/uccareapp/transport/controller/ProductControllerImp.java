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

import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;
import co.edu.icesi.dev.uccareapp.transport.service.ProductService;

@Controller
public class ProductControllerImp implements ProductController{
    
    @Autowired
    ProductService productservice;

    @GetMapping("/products")
    public String index(Model model) {
        model.addAttribute("products", productservice.findAll());
        return "products/index";
    }

    @GetMapping("/products/add")
    public String showSaveForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/add-product";
    }

    @PostMapping("/products/add") 
    public String saveBillofmaterial(@Validated @ModelAttribute Product product, BindingResult bindingResult, 
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "products/add-product";
            }
            
            productservice.saveProduct(product);
        }
        return "redirect:/products/";
    }

    @GetMapping("/products/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Product> productop = productservice.findById(id);
        if (productop == null)
            throw new IllegalArgumentException("Invalid product Id:" + id);
        model.addAttribute("product", productop.get());
        return "products/update-product";
    }

    @PostMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, 
            @Validated @ModelAttribute Product product, BindingResult bindingResult, Model model ) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "products/update-product";
            }
            productservice.editProduct(product);
        }
        return "redirect:/products";
    }

    @GetMapping("/products/del/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model) {
        Product product = productservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        productservice.delete(product);
        return "redirect:/products";
    }
}
