package przemek.spring.petclinic.service;

import przemek.spring.petclinic.model.Person;
import przemek.spring.petclinic.model.Vet;

public interface VetService<V extends Person, L extends Number> extends CrudService<Vet, Long> {
}
