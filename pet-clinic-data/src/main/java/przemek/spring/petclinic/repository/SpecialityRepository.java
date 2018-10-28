package przemek.spring.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import przemek.spring.petclinic.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
