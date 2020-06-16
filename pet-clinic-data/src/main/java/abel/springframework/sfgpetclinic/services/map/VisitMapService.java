package abel.springframework.sfgpetclinic.services.map;

import abel.springframework.sfgpetclinic.exceptions.ServiceException;
import abel.springframework.sfgpetclinic.model.Visit;
import abel.springframework.sfgpetclinic.services.VisitService;

import java.util.Set;

public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {
    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit visit) {
        if(visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null) {
            throw new ServiceException(this.getClass().getName(), "Visit is invalid");
        }
        return super.save(visit);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
