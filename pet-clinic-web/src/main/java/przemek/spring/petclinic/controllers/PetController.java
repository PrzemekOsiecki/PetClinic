package przemek.spring.petclinic.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import przemek.spring.petclinic.model.Owner;
import przemek.spring.petclinic.model.Pet;
import przemek.spring.petclinic.model.PetType;
import przemek.spring.petclinic.service.OwnerService;
import przemek.spring.petclinic.service.PetService;
import przemek.spring.petclinic.service.PetTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;

@AllArgsConstructor
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    @ModelAttribute("types")
    Collection<PetType> populatePetTypes(Model model) {
        model.addAttribute("types", petTypeService.findAll());
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    Owner findPetOwner(@PathVariable Long ownerId) {
        return (Owner) ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    void initOwnerBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    String getCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String createPet(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model, HttpServletRequest request) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        pet.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
        pet.setOwner(owner);
//        owner.getPets().add(pet);
//        if (result.hasErrors()) {
//            model.put("pet", pet);
//            return "pets/createOrUpdatePetForm";
//        } else {
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
//        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String getUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String updatePet(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return "pets/createOrUpdatePetForm";
        } else {
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

}
