package przemek.spring.petclinic.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import przemek.spring.petclinic.model.Owner;
import przemek.spring.petclinic.service.OwnerService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("owners")
@AllArgsConstructor
public class OwnerController {

    private final OwnerService ownerServiceImpl;

    @InitBinder
    void setAllowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

//    @RequestMapping({"", "/", "/index", "/index.html", "/find"})
//    String listOwners(Model model) {
//        model.addAttribute("owners", ownerService.findAll());
//        return "owners/index";
//    }

    @GetMapping("/find")
    String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject("owner", ownerServiceImpl.findById(ownerId));
        return mav;
    }

    @GetMapping
    String processFindForm(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> owners = (List<Owner>) ownerServiceImpl.findAllByLastNameLike(owner.getLastName());

        if (owners.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (owners.size() == 1) {
            owner = owners.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", owners);
            return "owners/ownersList";
        }
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        } else {
            Owner savedOwner = (Owner) ownerServiceImpl.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerServiceImpl.findById(ownerId));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        } else {
            owner.setId(ownerId);
            Owner savedOwner = (Owner) ownerServiceImpl.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

}