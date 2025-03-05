package Pfe_Education.mongo.service.file.local;

import org.springframework.data.mongodb.repository.MongoRepository;
import Pfe_Education.mongo.entities.File;

import java.util.Optional;

public interface FileRepository extends MongoRepository<File, String> {
    Optional<File> findById(String id);
    Optional<File> findFileByFilename(String filename);
    boolean existsByFilename(String filename);
}