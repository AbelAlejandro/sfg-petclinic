package abel.springframework.sfgpetclinic.repositories;

import abel.springframework.sfgpetclinic.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}
