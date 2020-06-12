package abel.springframework.sfgpetclinic.services.map;

import abel.springframework.sfgpetclinic.model.Specialty;
import abel.springframework.sfgpetclinic.model.Vet;
import abel.springframework.sfgpetclinic.services.SpecialtyService;
import abel.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {
    private final SpecialtyService specialtyService;

    public VetMapService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        if(object != null && object.getSpecialties().isEmpty()) {
            saveVetsSpecialties(object);
        }
        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    private void saveVetsSpecialties(Vet object) {
        object.getSpecialties().forEach(specialty -> {
            if (specialty.getId() == null) {
                Specialty savedSpecialty = specialtyService.save(specialty);
                specialty.setId(savedSpecialty.getId());
            }
        });
    }
}
