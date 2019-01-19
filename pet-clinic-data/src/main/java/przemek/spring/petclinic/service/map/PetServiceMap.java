package przemek.spring.petclinic.service.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import przemek.spring.petclinic.model.Pet;
import przemek.spring.petclinic.service.PetService;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Pet Pet) {
        super.delete(Pet);
    }

    @Override
    public Pet save(Pet Pet) {
        return super.save(Pet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
