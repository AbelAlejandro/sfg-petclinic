package abel.springframework.sfgpetclinic.services.springdatajpa;

import abel.springframework.sfgpetclinic.model.Owner;
import abel.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSpringDataJPAServiceTest {
    private static final String ADDRESS = "Fake St. 123";
    private static final String CITY = "Dublin";
    private static final String TELEPHONE = "23423524524";
    private static final String FIRST_NAME = "Carlos";
    private static final String LAST_NAME = "Bala";
    private static final String ADDRESS_2 = "47-48, Temple Bar, Dublin 2, D02 N725";
    private static final String TELEPHONE_2 = "6725286";
    private static final String FIRST_NAME_2 = "Al";
    private static final String LAST_NAME_2 = "Berto";
    Owner OWNER = new Owner.Builder() //TODO: Implement Builder pattern
            .withAddress(ADDRESS)
            .withCity(CITY)
            .withPets(Collections.emptySet())
            .withTelephone(TELEPHONE)
            .withFirstName(FIRST_NAME)
            .withLastName(LAST_NAME)
            .build();
    Owner OWNER_2 = new Owner.Builder() //TODO: Implement Builder pattern
            .withAddress(ADDRESS_2)
            .withCity(CITY)
            .withPets(Collections.emptySet())
            .withTelephone(TELEPHONE_2)
            .withFirstName(FIRST_NAME_2)
            .withLastName(LAST_NAME_2)
            .build();
    Set<Owner> OWNER_SET = Sets.newSet(OWNER, OWNER_2);
    Long OWNER_ID;

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSpringDataJPAService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(OWNER);
        Owner owner = service.findByLastName(LAST_NAME);
        assertNotNull(owner);
        assertEquals(owner, OWNER);
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(OWNER_SET);
        Set<Owner> owners = service.findAll();
        assertEquals(owners.size(), OWNER_SET.size());
        assertEquals(owners, OWNER_SET);
    }

    @Test
    void findById() {
        when(ownerRepository.findById(any())).thenReturn(Optional.of(OWNER));
        Owner owner = service.findById(OWNER_ID);
        assertNotNull(owner);
        assertEquals(Optional.of(owner), Optional.of(OWNER));
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(OWNER);
        Owner owner = service.save(OWNER);
        assertNotNull(owner);
        assertEquals(owner, OWNER);
    }

    @Test
    void delete() {
        service.delete(OWNER);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(OWNER_ID);
        verify(ownerRepository).deleteById(any());
    }
}