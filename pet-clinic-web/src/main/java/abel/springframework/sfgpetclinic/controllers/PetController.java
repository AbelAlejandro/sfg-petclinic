package abel.springframework.sfgpetclinic.controllers;

import abel.springframework.sfgpetclinic.model.Owner;
import abel.springframework.sfgpetclinic.model.PetType;
import abel.springframework.sfgpetclinic.repositories.OwnerRepository;
import abel.springframework.sfgpetclinic.repositories.PetRepository;
import abel.springframework.sfgpetclinic.services.OwnerService;
import abel.springframework.sfgpetclinic.services.PetService;
import abel.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetRepository pets, PetService petService, OwnerRepository owners, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
