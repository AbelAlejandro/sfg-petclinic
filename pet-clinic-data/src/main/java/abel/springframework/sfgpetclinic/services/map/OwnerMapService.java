package abel.springframework.sfgpetclinic.services.map;

import abel.springframework.sfgpetclinic.model.Owner;
import abel.springframework.sfgpetclinic.model.Pet;
import abel.springframework.sfgpetclinic.services.OwnerService;
import abel.springframework.sfgpetclinic.services.PetService;
import abel.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public Owner save(Owner owner) {
        if(owner != null) {
            if(owner.getPets() != null) {
                saveOwnersPets(owner);
            }
            return super.save(owner);
        }
        else return null;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }

    private void saveOwnersPets(Owner owner) {
        owner.getPets().forEach(pet -> {
            if(pet.getPetType() != null) {
                savePetType(pet);
            } else throw new RuntimeException("Pet Type is required");
            if(pet.getId() == null) {
                setPetIdFromService(pet);
            }
        });
    }

    private void savePetType(Pet pet) {
        if (pet.getPetType().getId() == null) {
            pet.setPetType(petTypeService.save(pet.getPetType()));
        }
    }

    private void setPetIdFromService(Pet pet) {
        Pet savedPet = petService.save(pet);
        pet.setId(savedPet.getId());
    }
}
