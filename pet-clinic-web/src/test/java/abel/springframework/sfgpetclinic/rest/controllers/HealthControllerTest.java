package abel.springframework.sfgpetclinic.rest.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import abel.springframework.sfgpetclinic.model.HealthCheck;
import abel.springframework.sfgpetclinic.model.HealthCheck.Status;

@ExtendWith(MockitoExtension.class)
class HealthControllerTest {

  @InjectMocks
  HealthController healthController;

  @BeforeEach
  void setUp() {
    healthController = new HealthController();
  }

  @Test
  void itGetsHealthCheck() {
    HealthCheck check = healthController.getHealthCheck();
    assertNotNull(check);
    assert(check).getStatus().equals(Status.OK);
  }
}