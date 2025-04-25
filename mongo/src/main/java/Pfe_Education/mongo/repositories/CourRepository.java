package Pfe_Education.mongo.repositories;

import Pfe_Education.mongo.Entities.Cour;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CourRepository extends MongoRepository<Cour, String> {
    @Query(value = "{'_id': ?0}", fields = "{'titre': 1}")
    Cour findTitleById(String id);
}
