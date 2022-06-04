package co.edu.icesi.dev.uccareapp.transport.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderheaderServiceImp;

@Controller
public class QueryControllerImp implements QueryController {
   
    @Autowired
    PurchaseorderheaderServiceImp purchaseservice;

    @GetMapping("/queries")
    public String index(Model model) {
        return "queries/index";
    }

    @GetMapping("/queries/queryrange")
    public String showSaveForm(Model model) {
        model.addAttribute("date1", new  String());
        model.addAttribute("date2", new String());
        model.addAttribute("result", new Object());
        return "queries/bydates";
    }

    @PostMapping("/queries/queryrange") 
    public String saveBillofmaterial(@Validated @ModelAttribute String date1, @Validated @ModelAttribute String date2
    ,BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {

            if (bindingResult.hasErrors()) {
                return "queries/bydates";
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate ld1 = LocalDate.parse(date1, formatter);
            LocalDate ld2 = LocalDate.parse(date2, formatter);
             model.addAttribute("results", purchaseservice.findByDateRange(ld1, ld2));
        
        return "redirect:/queries/queriesrange";
    }


}
