package przemek.spring.petclinic.service;

import przemek.spring.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
