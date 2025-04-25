package Pfe_Education.mongo.service.cour;

import Pfe_Education.mongo.Entities.Cour;
import Pfe_Education.mongo.repositories.CourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourServiceImpl implements CourService {

    @Autowired
    private CourRepository courRepository;

    @Override
    public Cour addWorkshop(Cour workshop) {
        return courRepository.save(workshop);
    }

    @Override
    public List<Cour> getAllWorkshops() {
        return courRepository.findAll();
    }

    @Override
    public Optional<Cour> getWorkshopById(String id) {
        return courRepository.findById(id);
    }

    @Override
    public Cour updateWorkshop(String id, Cour workshop) {
        if (courRepository.existsById(id)) {
            workshop.setId(id);
            return courRepository.save(workshop);
        }
        return null;
    }

    @Override
    public void deleteWorkshop(String id) {
        courRepository.deleteById(id);
    }
    @Override
    public String getWorkshopTitleById(String id) {
        Cour workshop = courRepository.findTitleById(id);
        return workshop != null ? workshop.getTitre() : null;
    }
}
