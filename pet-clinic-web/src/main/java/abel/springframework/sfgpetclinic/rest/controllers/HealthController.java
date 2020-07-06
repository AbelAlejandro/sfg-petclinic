package abel.springframework.sfgpetclinic.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import abel.springframework.sfgpetclinic.model.HealthCheck;
import abel.springframework.sfgpetclinic.model.HealthCheck.Status;

@RequestMapping("/health")
@RestController
public class HealthController {
  public HealthController() {

  }

  @GetMapping
  public HealthCheck getHealthCheck() {
    return new HealthCheck.Builder().withStatus(Status.OK).build();
  }
}
