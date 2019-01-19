package przemek.spring.petclinic.service.map;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import przemek.spring.petclinic.model.Speciality;
import przemek.spring.petclinic.model.Vet;
import przemek.spring.petclinic.service.VetService;
import przemek.spring.petclinic.service.VetSpecialitiesService;

import java.util.Set;

@AllArgsConstructor
@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService<Vet, Long> {

    private final VetSpecialitiesService vetSpecialitiesService;

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public Vet save(Vet vet) {
        if (vet.getSpecialities().size() > 0)
            vet.getSpecialities().forEach(speciality -> {
                if (speciality.getId() == null) {
                    Speciality savedSpeciality = vetSpecialitiesService.save(speciality);
                    speciality.setId(savedSpeciality.getId());
                }
            });
        return super.save(vet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
