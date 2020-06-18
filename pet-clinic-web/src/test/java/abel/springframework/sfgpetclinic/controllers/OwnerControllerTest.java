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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    private static final String OWNERS_OWNER_DETAILS = "owners/ownerDetails";
    private static final String OWNERS_FIND_OWNERS = "owners/findOwners";
    private static final String OWNERS_OWNERS_LIST = "owners/ownersList";
    private static final String REDIRECT_OWNERS = "redirect:/owners/";
    private static final String CREATE_OR_UPDATE_OWNER = "owners/createOrUpdateOwnerForm";
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

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        OWNER.setId(1L);
        OWNER_2.setId(2L);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name(OWNERS_FIND_OWNERS))
                .andExpect(model().attributeExists("owner"));
        verifyNoInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(OWNER, OWNER_2));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name(OWNERS_OWNERS_LIST))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(OWNER));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS + "1"));
    }

    @Test
    void showOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(OWNER);

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name(OWNERS_OWNER_DETAILS))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(CREATE_OR_UPDATE_OWNER))
                .andExpect(model().attributeExists("owner"));
        verifyNoInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.save(any())).thenReturn(OWNER);

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS + "1"))
                .andExpect(model().attributeExists("owner"));
        verify(ownerService).save(any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(OWNER);

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(CREATE_OR_UPDATE_OWNER))
                .andExpect(model().attributeExists("owner"));
        verifyZeroInteractions(ownerService);
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        when(ownerService.save(any())).thenReturn(OWNER);

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS + "1"))
                .andExpect(model().attributeExists("owner"));
        verify(ownerService).save(any());
    }
}