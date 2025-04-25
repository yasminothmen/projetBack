package Pfe_Education.mongo.service.cour;

import Pfe_Education.mongo.Entities.Cour;
import java.util.List;
import java.util.Optional;

public interface CourService {
    Cour addWorkshop(Cour workshop);
    List<Cour> getAllWorkshops();
    Optional<Cour> getWorkshopById(String id);
    Cour updateWorkshop(String id, Cour workshop);
    void deleteWorkshop(String id);
    String getWorkshopTitleById(String id);
}
