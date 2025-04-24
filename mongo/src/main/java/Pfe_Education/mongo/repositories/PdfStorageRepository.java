package Pfe_Education.mongo.repositories;

import Pfe_Education.mongo.Entities.PdfStorage;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PdfStorageRepository extends MongoRepository<PdfStorage, String> {
    List<PdfStorage> findByEntityTypeAndEntityName(String entityType, String entityName);
}