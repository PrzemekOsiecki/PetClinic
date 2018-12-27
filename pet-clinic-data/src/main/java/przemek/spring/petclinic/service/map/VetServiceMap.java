package przemek.spring.petclinic.service.map;

import org.springframework.stereotype.Service;
import przemek.spring.petclinic.model.Vet;
import przemek.spring.petclinic.service.VetService;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService<Vet, Long> {

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Vet Vet) {
        super.delete(Vet);
    }

    @Override
    public Vet save(Vet Vet) {
        return super.save(Vet.getId(), Vet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
