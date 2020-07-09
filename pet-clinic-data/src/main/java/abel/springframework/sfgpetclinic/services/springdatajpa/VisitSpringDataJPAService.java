package abel.springframework.sfgpetclinic.services.springdatajpa;

import abel.springframework.sfgpetclinic.model.Visit;
import abel.springframework.sfgpetclinic.repositories.VisitRepository;
import abel.springframework.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class VisitSpringDataJPAService implements VisitService {
    private final VisitRepository visitRepository;

    public VisitSpringDataJPAService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public List<Visit> findAll() {
        return new ArrayList<>(visitRepository.findAll());
    }

    @Override
    public Visit findById(Long aLong) {
        return visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        visitRepository.deleteById(aLong);
    }
}
