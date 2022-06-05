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

import co.edu.icesi.dev.uccareapp.transport.delegate.ShipmethodDelegate;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;

@Controller
public class ShipmethodControllerImp implements ShipmethodController {
    
    @Autowired
    ShipmethodDelegate shipmethoddelegate;

    @GetMapping("/shipmethods")
    public String index(Model model) {
        model.addAttribute("shipmethods", shipmethoddelegate.findAll());
        return "shipmethods/index";
    }

    @GetMapping("/shipmethods/add")
    public String showSaveForm(Model model) {
        model.addAttribute("shipmethod", new Shipmethod());
        return "shipmethods/add-shipmethod";
    }

    @PostMapping("/shipmethods/add") 
    public String saveShipmethod(@Validated @ModelAttribute Shipmethod shipmethod, BindingResult bindingResult, 
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "shipmethods/add-shipmethod";
            }
            shipmethoddelegate.saveShipmethod(shipmethod);
        }
        return "redirect:/shipmethods/";
    }

    @GetMapping("/shipmethods/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("shipmethod", shipmethoddelegate.findById(id));
        return "shipmethods/update-shipmethod";
    }

    @PostMapping("/shipmethods/edit/{id}")
    public String updateShipmethod(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, 
            @Validated @ModelAttribute Shipmethod shipmethod, BindingResult bindingResult, Model model ) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "shipmethods/update-shipmethod";
            }
            shipmethoddelegate.editShipmethod(shipmethod);
        }
        return "redirect:/shipmethods";
    }

    @GetMapping("/shipmethods/del/{id}")
    public String deleteShipmethod(@PathVariable("id") Integer id, Model model) {
        shipmethoddelegate.delete(shipmethoddelegate.findById(id));
        return "redirect:/shipmethods";
    }
}
