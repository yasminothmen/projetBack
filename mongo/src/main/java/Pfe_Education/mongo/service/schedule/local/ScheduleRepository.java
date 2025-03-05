package Pfe_Education.mongo.service.schedule.local;

import Pfe_Education.mongo.entities.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    // Tu peux ajouter des méthodes spécifiques si nécessaire
}
