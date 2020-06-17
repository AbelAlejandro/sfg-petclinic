package abel.springframework.sfgpetclinic.services.map;

import abel.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    private static final String ADDRESS = "Fake St. 123";
    private static final String CITY = "Dublin";
    private static final String TELEPHONE = "23423524524";
    private static final String FIRST_NAME = "Carlos";
    private static final String LAST_NAME = "Bala";
    Owner OWNER = new Owner.Builder()
            .withAddress(ADDRESS)
            .withCity(CITY)
            .withPets(Collections.emptySet())
            .withTelephone(TELEPHONE)
            .withFirstName(FIRST_NAME)
            .withLastName(LAST_NAME)
            .build();
    Long OWNER_ID;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        OWNER_ID = ownerMapService.save(OWNER).getId();
    }

    @Test
    void itFindsAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void itDeletesById() {
        ownerMapService.deleteById(OWNER_ID);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void itDeltes() {
        ownerMapService.delete(OWNER);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void itSaves() {
        Owner owner = ownerMapService.save(OWNER);
        assertEquals(owner, OWNER);
    }

    @Test
    void itFindsById() {
        Owner owner = ownerMapService.findById(OWNER_ID);
        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void itFindsByLastName() {
        Owner owner = ownerMapService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, owner.getLastName());
    }
}