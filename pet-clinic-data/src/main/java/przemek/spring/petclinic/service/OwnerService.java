package przemek.spring.petclinic.service;

import przemek.spring.petclinic.model.Owner;
import przemek.spring.petclinic.model.Person;

import java.util.List;

public interface OwnerService<O extends Person, L extends Number> extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
