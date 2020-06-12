package abel.springframework.sfgpetclinic.bootstrap;

import abel.springframework.sfgpetclinic.model.Owner;
import abel.springframework.sfgpetclinic.model.Pet;
import abel.springframework.sfgpetclinic.model.PetType;
import abel.springframework.sfgpetclinic.model.Vet;
import abel.springframework.sfgpetclinic.services.OwnerService;
import abel.springframework.sfgpetclinic.services.PetTypeService;
import abel.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType catType = new PetType();
        catType.setName("Cat");

        PetType savedCatType = petTypeService.save(catType);

        PetType dogType = new PetType();
        dogType.setName("Dog");

        PetType savedDogType = petTypeService.save(dogType);

        System.out.println("Loaded Pet Types....");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("Grote Markt 18");
        owner1.setCity("Zwolle");
        owner1.setTelephone("234234234234");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedCatType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now().minusYears(7).minusMonths(8).minusWeeks(3).minusDays(2));
        mikesPet.setName("Nodo");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("Diemerzeedijk 100");
        owner2.setCity("Diemen");
        owner2.setTelephone("43546456");

        Pet fionasPet = new Pet();
        fionasPet.setPetType(savedDogType);
        fionasPet.setOwner(owner2);
        fionasPet.setBirthDate(LocalDate.now().minusYears(2).minusMonths(3).minusWeeks(1).minusDays(5));

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
