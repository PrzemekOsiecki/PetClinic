package przemek.spring.petclinic.service.springdatajpaservice;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import przemek.spring.petclinic.model.Speciality;
import przemek.spring.petclinic.repository.VetSpecialityRepository;
import przemek.spring.petclinic.service.VetSpecialitiesService;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class VetSpecialityServieImpl implements VetSpecialitiesService {

    VetSpecialityRepository vetSpecialityRepository;

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        vetSpecialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long id) {
        return vetSpecialityRepository.findById(id).orElse(null);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return vetSpecialityRepository.save(speciality);
    }

    @Override
    public void delete(Speciality speciality) {
        vetSpecialityRepository.delete(speciality);
    }

    @Override
    public void deleteById(Long id) {
        vetSpecialityRepository.deleteById(id);
    }
}
