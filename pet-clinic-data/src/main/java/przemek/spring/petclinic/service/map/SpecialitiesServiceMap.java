package przemek.spring.petclinic.service.map;

import org.springframework.stereotype.Service;
import przemek.spring.petclinic.model.Speciality;
import przemek.spring.petclinic.service.SpecialitiesService;

import java.util.Set;

@Service
public class SpecialitiesServiceMap extends AbstractMapService<Speciality, Long> implements SpecialitiesService {

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Speciality Speciality) {
        super.delete(Speciality);
    }

    @Override
    public Speciality save(Speciality Speciality) {
        return super.save(Speciality);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
