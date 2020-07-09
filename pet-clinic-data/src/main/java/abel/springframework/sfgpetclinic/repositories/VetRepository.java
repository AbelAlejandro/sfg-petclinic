package abel.springframework.sfgpetclinic.repositories;

import abel.springframework.sfgpetclinic.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Vet, Long> {
}
