package Pfe_Education.mongo.service.user.local;

import Pfe_Education.mongo.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<UserEntity, String> {

    // Vérifie si un UserEntity existe par son username
    boolean existsByUsername(String username);

    // Trouve un UserEntity par son username
    Optional<UserEntity> findByUsername(String username);

    // Requête MongoDB pour trouver un utilisateur par username
    @Query("{ 'username' : ?0 }")
    Optional<UserEntity> findUserByUsername(String username);

    // Vérifie si un UserEntity existe par son username (requête MongoDB avec "exists")
    @Query(value = "{ 'username' : ?0 }", exists = true)
    boolean existsByUsernameJPQL(String username);
}
