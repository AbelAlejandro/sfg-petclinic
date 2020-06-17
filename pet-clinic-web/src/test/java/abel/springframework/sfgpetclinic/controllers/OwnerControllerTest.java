package abel.springframework.sfgpetclinic.controllers;

import abel.springframework.sfgpetclinic.model.Owner;
import abel.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    private static final String ADDRESS = "Fake St. 123";
    private static final String CITY = "Dublin";
    private static final String TELEPHONE = "23423524524";
    private static final String FIRST_NAME = "Carlos";
    private static final String LAST_NAME = "Bala";
    private static final String ADDRESS_2 = "47-48, Temple Bar, Dublin 2, D02 N725";
    private static final String TELEPHONE_2 = "6725286";
    private static final String FIRST_NAME_2 = "Al";
    private static final String LAST_NAME_2 = "Berto";
    Owner OWNER = new Owner.Builder()
            .withAddress(ADDRESS)
            .withCity(CITY)
            .withPets(Collections.emptySet())
            .withTelephone(TELEPHONE)
            .withFirstName(FIRST_NAME)
            .withLastName(LAST_NAME)
            .build();
    Owner OWNER_2 = new Owner.Builder()
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
    OwnerService service;

    @InjectMocks
    OwnerController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void listOwners() throws Exception {
        when(service.findAll()).thenReturn(OWNER_SET);
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("unimplemented"));
        verifyNoInteractions(service);
    }
}