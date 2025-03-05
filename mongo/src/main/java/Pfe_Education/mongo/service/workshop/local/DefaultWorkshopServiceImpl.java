package Pfe_Education.mongo.service.workshop.local;

import Pfe_Education.mongo.entities.WorkshopEntity;
import Pfe_Education.mongo.service.workshop.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultWorkshopServiceImpl implements WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Override
    public WorkshopEntity addWorkshop(WorkshopEntity workshop) {
        return workshopRepository.save(workshop);
    }

    @Override
    public List<WorkshopEntity> getAllWorkshops() {
        return workshopRepository.findAll();
    }

    @Override
    public Optional<WorkshopEntity> getWorkshopById(String id) {
        return workshopRepository.findById(id);
    }

    @Override
    public WorkshopEntity updateWorkshop(String id, WorkshopEntity workshop) {
        if (workshopRepository.existsById(id)) {
            workshop.setId(id);
            return workshopRepository.save(workshop);
        }
        return null;
    }

    @Override
    public void deleteWorkshop(String id) {
        workshopRepository.deleteById(id);
    }
}
