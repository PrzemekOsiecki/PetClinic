package przemek.spring.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import przemek.spring.petclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
