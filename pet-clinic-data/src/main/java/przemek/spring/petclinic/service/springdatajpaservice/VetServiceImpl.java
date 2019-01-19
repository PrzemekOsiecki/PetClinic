package przemek.spring.petclinic.service.springdatajpaservice;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import przemek.spring.petclinic.model.Vet;
import przemek.spring.petclinic.repository.VetRepository;
import przemek.spring.petclinic.service.VetService;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class VetServiceImpl implements VetService<Vet, Long> {

    private final VetRepository vetRepository;

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }
}
