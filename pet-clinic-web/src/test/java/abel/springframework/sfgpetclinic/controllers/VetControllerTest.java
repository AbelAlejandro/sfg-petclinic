package abel.springframework.sfgpetclinic.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;
import java.util.Set;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import abel.springframework.sfgpetclinic.model.Vet;
import abel.springframework.sfgpetclinic.services.VetService;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {
  private static final Vet VET_1 = new Vet.Builder()
      .withId(1)
      .build();
  private static final Vet VET_2 = new Vet.Builder()
      .withId(2)
      .build();
  private static final List<Vet> VET_SET = Lists.newArrayList(VET_1, VET_2);

  @Mock
  VetService vetService;

  @InjectMocks
  VetController controller;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders
        .standaloneSetup(controller)
        .build();
  }

  @Test
  void itGetsListOfVets() throws Exception {
    when(vetService.findAll()).thenReturn(VET_SET);

    mockMvc.perform(get("/vets"))
        .andExpect(status().isOk())
        .andExpect(view().name("vets/index"))
        .andExpect(model().attribute("vets", hasSize(2)));
  }
}