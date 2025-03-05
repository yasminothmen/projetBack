package Pfe_Education.mongo.service.workshop.local;

import Pfe_Education.mongo.entities.WorkshopEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkshopRepository extends MongoRepository<WorkshopEntity, String> {
}
