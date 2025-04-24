package Pfe_Education.mongo.service.cour;

import Pfe_Education.mongo.Entities.WorkshopEntity;
import java.util.List;
import java.util.Optional;

public interface WorkshopService {
    WorkshopEntity addWorkshop(WorkshopEntity workshop);
    List<WorkshopEntity> getAllWorkshops();
    Optional<WorkshopEntity> getWorkshopById(String id);
    WorkshopEntity updateWorkshop(String id, WorkshopEntity workshop);
    void deleteWorkshop(String id);
    String getWorkshopTitleById(String id);
}
