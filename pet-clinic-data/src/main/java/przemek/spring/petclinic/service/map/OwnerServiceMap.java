package przemek.spring.petclinic.service.map;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import przemek.spring.petclinic.model.Owner;
import przemek.spring.petclinic.model.Pet;
import przemek.spring.petclinic.model.PetType;
import przemek.spring.petclinic.service.OwnerService;
import przemek.spring.petclinic.service.PetService;
import przemek.spring.petclinic.service.PetTypeService;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService<Owner, Long> {

    private final PetService petService;
    private final PetTypeService petTypeService;

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public Owner save(Owner owner) {
        if(owner != null) {
            Optional.ofNullable(owner.getPets()).ifPresent(pets -> {
                pets.forEach(pet -> {
                    Optional<PetType> petType = Optional.ofNullable(pet.getPetType());
                        petType.ifPresent(existingPetType -> {
                            if(existingPetType.getId() == null) {
                                pet.setPetType(petTypeService.save(existingPetType));
                            }
                        });
                    petType.orElseThrow(() -> new RuntimeException("PetType is required"));

                    if(pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            });
        }
        return super.save(owner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }
}
