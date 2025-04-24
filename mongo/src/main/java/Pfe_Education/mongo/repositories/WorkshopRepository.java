package Pfe_Education.mongo.repositories;

import Pfe_Education.mongo.Entities.WorkshopEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface WorkshopRepository extends MongoRepository<WorkshopEntity, String> {
    @Query(value = "{'_id': ?0}", fields = "{'titre': 1}")
    WorkshopEntity findTitleById(String id);
}
