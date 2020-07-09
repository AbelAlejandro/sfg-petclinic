package abel.springframework.sfgpetclinic.repositories;

import abel.springframework.sfgpetclinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
