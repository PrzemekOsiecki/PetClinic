package przemek.spring.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import przemek.spring.petclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
