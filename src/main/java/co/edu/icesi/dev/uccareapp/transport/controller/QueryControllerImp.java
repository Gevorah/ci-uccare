package co.edu.icesi.dev.uccareapp.transport.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.icesi.dev.uccareapp.transport.delegate.PurchaseorderheaderDelegateImp;
import co.edu.icesi.dev.uccareapp.transport.model.formsSubmit.DateRange;

@Controller
public class QueryControllerImp implements QueryController {
   
    @Autowired
    PurchaseorderheaderDelegateImp purchaseorderheaderdelegate;

    @GetMapping("/queries")
    public String index(Model model) {
        return "queries/index";
    }

    @GetMapping("/queries/query=range")
    public String showSaveForm(Model model) {
        model.addAttribute("daterange", new DateRange());
        return "queries/bydates";
    }

    @PostMapping("/queries/query=range{start}+{end}") 
    public String QuerybyDates(@ModelAttribute DateRange daterange, Model model
     ) {

        model.addAttribute("results", purchaseorderheaderdelegate
                    .findByDateRange(daterange.getStart(), daterange.getEnd()));
        
        return "queries/results";
    }

    @GetMapping("/queries/query=two")
    public String showQuery(Model model) {
        model.addAttribute("results", purchaseorderheaderdelegate.findByTwoDetails());
        return "queries/byrestriction";
    }
}
