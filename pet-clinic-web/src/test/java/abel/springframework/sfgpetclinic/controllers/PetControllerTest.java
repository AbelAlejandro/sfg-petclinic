package abel.springframework.sfgpetclinic.controllers;

import abel.springframework.sfgpetclinic.model.Owner;
import abel.springframework.sfgpetclinic.model.Pet;
import abel.springframework.sfgpetclinic.model.PetType;
import abel.springframework.sfgpetclinic.services.OwnerService;
import abel.springframework.sfgpetclinic.services.PetService;
import abel.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {
    private static final String REDIRECT_OWNERS = "redirect:/owners/1";
    private static final String ADDRESS = "Fake St. 123";
    private static final String CITY = "Dublin";
    private static final String TELEPHONE = "23423524524";
    private static final String FIRST_NAME = "Carlos";
    private static final String LAST_NAME = "Bala";
    private static final PetType PET_TYPE = new PetType.Builder().withName("Cat").build();
    private static final PetType PET_TYPE_2 = new PetType.Builder().withName("Dog").build();
    private static final Set<PetType> PET_TYPE_SET = Sets.newSet(PET_TYPE, PET_TYPE_2);
    private static final Pet PET = new Pet.Builder()
            .withPetType(PET_TYPE)
            .withBirthDate(LocalDate.now())
            .withName("Nodo")
            .withId(1L)
            .build();
    private static final Owner OWNER = new Owner.Builder()
            .withAddress(ADDRESS)
            .withCity(CITY)
            .withPets(Collections.singleton(PET))
            .withTelephone(TELEPHONE)
            .withFirstName(FIRST_NAME)
            .withLastName(LAST_NAME)
            .build();
    private static final String CREATE_OR_UPDATE_PET = "pets/createOrUpdatePetForm";
    public static final String NEW_PET_REQUEST = "/owners/1/pets/new";
    private static final String EDIT_PET_REQUEST = "/owners/1/pets/1/edit";


    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    @Mock
    PetService petService;

    @InjectMocks
    PetController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        OWNER.setId(1L);
        PET.setOwner(OWNER);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void initCreationForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(OWNER);
        when(petTypeService.findAll()).thenReturn(PET_TYPE_SET);

        mockMvc.perform(get(NEW_PET_REQUEST))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name(CREATE_OR_UPDATE_PET));
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(OWNER);
        when(petTypeService.findAll()).thenReturn(PET_TYPE_SET);

        mockMvc.perform(post(NEW_PET_REQUEST))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS))
                .andExpect(model().attributeExists("owner"));
        verify(petService).save(any());
    }

    @Test
    void initUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(OWNER);
        when(petTypeService.findAll()).thenReturn(PET_TYPE_SET);
        when(petService.findById(anyLong())).thenReturn(PET);

        mockMvc.perform(get(EDIT_PET_REQUEST))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name(CREATE_OR_UPDATE_PET));
    }

    @Test
    void processUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(OWNER);
        when(petTypeService.findAll()).thenReturn(PET_TYPE_SET);

        mockMvc.perform(post(EDIT_PET_REQUEST))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS));
        verify(petService).save(any());
    }
}