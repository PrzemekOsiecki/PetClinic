package przemek.spring.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import przemek.spring.petclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
