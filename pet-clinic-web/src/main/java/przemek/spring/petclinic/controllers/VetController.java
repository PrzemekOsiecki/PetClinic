package przemek.spring.petclinic.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import przemek.spring.petclinic.service.VetService;

@Controller
@AllArgsConstructor
public class VetController {

    private VetService vetService;

    @RequestMapping({"/vets", "/vets/index", "/vets/index.html"})
    String index(Model model) {

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
