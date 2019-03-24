package przemek.spring.petclinic.bootstrap;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import przemek.spring.petclinic.model.*;
import przemek.spring.petclinic.service.*;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final VetSpecialitiesService vetSpecialitiesService;
    private final VisitService visitService;

    @Override
    public void run(String... args) throws Exception {

        int count = (int) petTypeService.findAll().stream().count();

        if (count == 0)
            loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = vetSpecialitiesService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = vetSpecialitiesService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = vetSpecialitiesService.save(dentistry);

        Owner owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setAddress("123 Street");
        owner.setCity("NowhereLand");
        owner.setTelephone("123456789");

        Pet johnPet = new Pet();
        johnPet.setPetType(savedDogPetType);
        johnPet.setOwner(owner);
        johnPet.setBirthDate(LocalDate.now());
        johnPet.setName("Bucky");

        owner.getPets().add(johnPet);

        ownerService.save(owner);

        Owner owner2 = new Owner();
        owner2.setFirstName("Johny");
        owner2.setLastName("Bravo");
        owner2.setAddress("345 Xyz");
        owner2.setCity("SomewhereLand");
        owner2.setTelephone("111222333");

        Pet johnyPet = new Pet();
        johnyPet.setPetType(savedDogPetType);
        johnyPet.setOwner(owner2);
        johnyPet.setBirthDate(LocalDate.now());
        johnyPet.setName("Rosco");

        owner2.getPets().add(johnyPet);

        ownerService.save(owner2);

        Visit johnPetVisit = new Visit();
        johnPetVisit.setPet(johnPet);
        johnPetVisit.setDate(LocalDate.now());
        johnPetVisit.setDescription("Sneeze dog");

        visitService.save(johnPetVisit);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sally");
        vet1.setLastName("Adams");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Gina");
        vet2.setLastName("Baj");
        vet2.getSpecialities().add(savedDentistry);

        vetService.save(vet2);

        System.out.println("Data loaded ");
    }
}
