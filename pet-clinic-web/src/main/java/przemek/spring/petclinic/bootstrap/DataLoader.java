package przemek.spring.petclinic.bootstrap;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import przemek.spring.petclinic.model.Owner;
import przemek.spring.petclinic.model.Vet;
import przemek.spring.petclinic.service.OwnerService;
import przemek.spring.petclinic.service.VetService;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    @Override
    public void run(String... args) throws Exception {

        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("John");
        owner.setLastName("Doe");

        ownerService.save(owner);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Johny");
        owner2.setLastName("Bravo");

        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sally");
        vet1.setLastName("Adams");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Gina");
        vet2.setLastName("Baj");

        vetService.save(vet2);

        System.out.println("Data loaded ");
    }
}
