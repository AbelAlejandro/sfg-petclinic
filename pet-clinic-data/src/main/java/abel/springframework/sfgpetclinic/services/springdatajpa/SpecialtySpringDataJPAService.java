package abel.springframework.sfgpetclinic.services.springdatajpa;

import abel.springframework.sfgpetclinic.model.Specialty;
import abel.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import abel.springframework.sfgpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class SpecialtySpringDataJPAService implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtySpringDataJPAService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public List<Specialty> findAll() {
        return new ArrayList<>(specialtyRepository.findAll());
    }

    @Override
    public Specialty findById(Long aLong) {
        return specialtyRepository.findById(aLong).orElse(null);
    }

    @Override
    public Specialty save(Specialty object) {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Specialty object) {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialtyRepository.deleteById(aLong);
    }
}
