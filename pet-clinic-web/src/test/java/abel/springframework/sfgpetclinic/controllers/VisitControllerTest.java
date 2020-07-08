package abel.springframework.sfgpetclinic.controllers;

import abel.springframework.sfgpetclinic.model.Owner;
import abel.springframework.sfgpetclinic.model.Pet;
import abel.springframework.sfgpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.OrderWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import abel.springframework.sfgpetclinic.services.PetService;
import abel.springframework.sfgpetclinic.services.VisitService;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
  private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
  private static final String REDIRECT_OWNERS_1 = "redirect:/owners/{ownerId}";
  private static final String YET_ANOTHER_VISIT_DESCRIPTION = "yet another visit";
  private static final PetType PET_TYPE = new PetType.Builder().withName("Cat").build();
  private static final String ADDRESS = "Fake St. 123";
  private static final String CITY = "Dublin";
  private static final String TELEPHONE = "23423524524";
  private static final String FIRST_NAME = "Carlos";
  private static final String LAST_NAME = "Bala";
  private static  final Owner OWNER = new Owner.Builder()
          .withId(1L)
          .withAddress(ADDRESS)
          .withCity(CITY)
          .withPets(Collections.emptySet())
          .withTelephone(TELEPHONE)
          .withFirstName(FIRST_NAME)
          .withLastName(LAST_NAME)
          .build();
  private static final Pet PET = new Pet.Builder()
          .withOwner(OWNER)
          .withPetType(PET_TYPE)
          .withBirthDate(LocalDate.now())
          .withName("Nodo")
          .withId(1L)
          .build();
  @Mock
  PetService petService;

  @Mock
  VisitService visitService;

  @InjectMocks
  VisitController controller;

  MockMvc mockMvc;

  private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
  private final Map<String, String> uriVariables = new HashMap<>();
  private URI visitsUri;

  @BeforeEach
  void setUp() {
    Long petId = 1L;
    Long ownerId = 1L;
    when(petService.findById(anyLong()))
            .thenReturn(PET);

    uriVariables.clear();
    uriVariables.put("ownerId", ownerId.toString());
    uriVariables.put("petId", petId.toString());
    visitsUri = visitsUriTemplate.expand(uriVariables);

    mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .build();
  }

  @Test
  void initNewVisitForm() throws Exception {
    mockMvc.perform(get(visitsUri))
            .andExpect(status().isOk())
            .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
  }


  @Test
  void processNewVisitForm() throws Exception {
    mockMvc.perform(post(visitsUri)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("date","2018-11-11")
            .param("description", YET_ANOTHER_VISIT_DESCRIPTION))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name(REDIRECT_OWNERS_1))
            .andExpect(model().attributeExists("visit"));
  }
}