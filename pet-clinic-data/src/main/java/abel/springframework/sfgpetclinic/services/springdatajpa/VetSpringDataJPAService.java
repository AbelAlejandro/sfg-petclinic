package abel.springframework.sfgpetclinic.services.springdatajpa;

import abel.springframework.sfgpetclinic.model.Vet;
import abel.springframework.sfgpetclinic.repositories.VetRepository;
import abel.springframework.sfgpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class VetSpringDataJPAService implements VetService {
    private final VetRepository vetRepository;

    public VetSpringDataJPAService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public List<Vet> findAll() {
        return new ArrayList<>(vetRepository.findAll());
    }

    @Override
    public Vet findById(Long aLong) {
        return vetRepository.findById(aLong).orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        vetRepository.deleteById(aLong);
    }
}
