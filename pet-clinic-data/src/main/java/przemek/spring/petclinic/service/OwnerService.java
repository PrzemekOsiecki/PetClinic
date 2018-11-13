package przemek.spring.petclinic.service;

import przemek.spring.petclinic.model.Owner;
import przemek.spring.petclinic.model.Person;

public interface OwnerService<O extends Person, L extends Number> extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
