package abel.springframework.sfgpetclinic.services.springdatajpa;

import abel.springframework.sfgpetclinic.model.Pet;
import abel.springframework.sfgpetclinic.repositories.PetRepository;
import abel.springframework.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class PetSpringDataJPAService implements PetService {
    private final PetRepository petRepository;

    public PetSpringDataJPAService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> findAll() {
        return new ArrayList<>(petRepository.findAll());
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }
}
