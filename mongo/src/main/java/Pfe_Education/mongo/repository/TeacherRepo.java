package Pfe_Education.mongo.repository;

import Pfe_Education.mongo.Entities.TeacherEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends MongoRepository<TeacherEntity, String> {

    // Vérifie si un enseignant existe par son username
    boolean existsByUsername(String username);

    // Trouve un enseignant par son username
    TeacherEntity findByUsername(String username);

    // Trouve un enseignant par son username (équivalent à finduserByUsername en JPA)
    @Query("{ 'username' : ?0 }")
    TeacherEntity finduserByUsername(String username);

    // Vérifie si un enseignant existe par son username (équivalent à existsByUsernameJPQL en JPA)
    @Query(value = "{ 'username' : ?0 }", exists = true)
    boolean existsByUsernameJPQL(String username);




}