package Pfe_Education.mongo.service.schoolyear.local;

import Pfe_Education.mongo.Entities.SchoolYear;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchoolYearRepository extends MongoRepository<SchoolYear, String> {
}
