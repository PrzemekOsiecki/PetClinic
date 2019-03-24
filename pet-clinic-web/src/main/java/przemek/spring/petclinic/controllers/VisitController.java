package przemek.spring.petclinic.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import przemek.spring.petclinic.model.Pet;
import przemek.spring.petclinic.model.Visit;
import przemek.spring.petclinic.service.PetService;
import przemek.spring.petclinic.service.VisitService;

import javax.validation.Valid;
import java.util.Map;

@AllArgsConstructor
@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    @InitBinder
    void setAllowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    Visit getPetWithAssociatedVisit(@PathVariable("petId") long petId, Map<String, Object> model) {
        Pet pet = petService.findById(petId);
        model.put("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") Long petId, Map<String, Object> model) {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }

}
