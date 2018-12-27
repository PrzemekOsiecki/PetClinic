package przemek.spring.petclinic.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import przemek.spring.petclinic.service.OwnerService;

@Controller
@AllArgsConstructor
@RequestMapping("owners")
public class OwnerController {

    private final OwnerService ownerService;

    @RequestMapping({"/", "/index", "/index.html"})
    String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }
}
