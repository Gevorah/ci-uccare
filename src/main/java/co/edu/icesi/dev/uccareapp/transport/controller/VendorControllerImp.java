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

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;
import co.edu.icesi.dev.uccareapp.transport.service.VendorService;

@Controller
public class VendorControllerImp implements VendorController {

	VendorService vendorservice;

	@Autowired
	public VendorControllerImp(VendorService vendorservice) {
		this.vendorservice = vendorservice;
	}

	@GetMapping("/vendors/")
	public String indexUser(Model model) {
		model.addAttribute("vendors", vendorservice.findAll());
		return "vendors/index";
	}

	@GetMapping("/vendors/add-vendor")
	public String showSaveForm(Model model) {
		model.addAttribute("vendor", new Vendor());
		return "vendors/add-vendor";
	}
	
	@PostMapping("/vendors/add-vendor") 
	public String saveVendor(@Validated @ModelAttribute Vendor vendor, BindingResult bindingResult, 
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "vendors/add-vendor";
			}
			vendorservice.saveVendor(vendor);
		}
		return "redirect:/vendors/";
	}

	@GetMapping("/vendors/del/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		Vendor vendor = vendorservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid vendor Id:" + id));
		vendorservice.delete(vendor);
		model.addAttribute("vendors", vendorservice.findAll());
		return "vendors/index";
	}

	@GetMapping("/vendors/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Optional<Vendor> vendor = vendorservice.findById(id);
		if (vendor == null)
			throw new IllegalArgumentException("Invalid vendor Id:" + id);
		model.addAttribute("vendor", vendor.get());
		//model.addAttribute("types", vendorservice.getTypes());
		return "vendors/update-vendor";
	}

	@PostMapping("/vendors/edit/{id}")
	public String updateVendor(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, 
			@Validated @ModelAttribute Vendor vendor, BindingResult bindingResult, Model model ) {
		if (action != null && action.equals("Cancel")) {
			System.out.println("---------entra");
			return "redirect:/vendors/";
		} else {
			model.addAttribute("vendors", vendorservice.findAll());
		}
		return "redirect:/vendors/";
	}
}
