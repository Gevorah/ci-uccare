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

import co.edu.icesi.dev.uccareapp.transport.model.formsSubmit.Orderbydates;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
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
       /*  model.addAttribute("date1", new  String());
        model.addAttribute("date2", new String());*/
        model.addAttribute("orderbydates", new Orderbydates());
        return "queries/bydates";
    }

    @PostMapping("/queries/results") 
    public String QuerybyDates(@ModelAttribute Orderbydates orderbydates, Model model
     ) {

             model.addAttribute("results", purchaseservice.findByDateRange(orderbydates.getDate1()
             , orderbydates.getDate2()));
        
        return "queries/results";
    }

    @GetMapping("/queries/queryTwo")
    public String showQuery(Model model) {
        model.addAttribute("results", purchaseservice.findByTwoDetails());
        return "queries/byrestriction";
    }


}
