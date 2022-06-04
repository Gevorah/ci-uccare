package co.edu.icesi.dev.uccareapp.transport.controller;

import org.springframework.stereotype.Controller;

@Controller
public class QueryControllerImp implements QueryController {
   
    @Autowired
    BillofmaterialService billofmaterialservice;

    @Autowired
    ProductService productservice;

    @GetMapping("/billofmaterials")
    public String index(Model model) {
        model.addAttribute("billofmaterials", billofmaterialservice.findAll());
        return "billofmaterials/index";
    }

    @GetMapping("/billofmaterials/add")
    public String showSaveForm(Model model) {
        model.addAttribute("billofmaterial", new Billofmaterial());
        model.addAttribute("products", productservice.findAll() );
        return "billofmaterials/add-billofmaterial";
    }

    @PostMapping("/billofmaterials/add") 
    public String saveBillofmaterial(@Validated @ModelAttribute Billofmaterial billofmaterial, BindingResult bindingResult, 
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "billofmaterials/add-billofmaterial";
            }
            
            billofmaterialservice.saveBillofmaterial(billofmaterial);
        }
        return "redirect:/billofmaterials/";
    }

    @GetMapping("/billofmaterials/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Billofmaterial> billofmaterialop = billofmaterialservice.findById(id);
        if (billofmaterialop == null)
            throw new IllegalArgumentException("Invalid billofmaterial Id:" + id);
        model.addAttribute("billofmaterial", billofmaterialop.get());
        model.addAttribute("products", productservice.findAll());
        return "billofmaterials/update-billofmaterial";
    }

    @PostMapping("/billofmaterials/edit/{id}")
    public String updateBillofmaterial(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, 
            @Validated @ModelAttribute Billofmaterial billofmaterial, BindingResult bindingResult, Model model ) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                return "billofmaterials/update-billofmaterial";
            }
            billofmaterialservice.editBillofmaterial(billofmaterial);
        }
        return "redirect:/billofmaterials/";
    }

    @GetMapping("/billofmaterials/del/{id}")
    public String deleteShipmethod(@PathVariable("id") Integer id, Model model) {
        Billofmaterial billofmaterial = billofmaterialservice.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid billofmaterial Id:" + id));
        billofmaterialservice.delete(billofmaterial);
        return "redirect:/billofmaterials/";
    }
}
