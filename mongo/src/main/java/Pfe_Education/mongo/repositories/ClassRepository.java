package Pfe_Education.mongo.repositories;

import Pfe_Education.mongo.Entities.ClassEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends MongoRepository<ClassEntity, String> {
    // Custom queries can be added here if needed
}
