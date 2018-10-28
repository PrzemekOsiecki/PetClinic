package przemek.spring.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import przemek.spring.petclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
