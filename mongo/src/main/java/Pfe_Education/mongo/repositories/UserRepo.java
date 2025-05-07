package Pfe_Education.mongo.repositories;

import Pfe_Education.mongo.Entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<UserEntity, String> {
    List<UserEntity> findByRole(String role); // Permet de récupérer les users par rôle


    // Vérifie si un UserEntity existe par son username
    boolean existsByUsername(String username);

    // Trouve un UserEntity par son username
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findProfileImageIdById(String userId);
    Optional<UserEntity> findByEmail(String email);

    // Requête MongoDB pour trouver un utilisateur par username
    @Query("{ 'username' : ?0 }")
    Optional<UserEntity> findUserByUsername(String username);

    // Vérifie si un UserEntity existe par son username (requête MongoDB avec "exists")
    @Query(value = "{ 'username' : ?0 }", exists = true)
    boolean existsByUsernameJPQL(String username);

    long countByRole(String student);

    @Query("{ '_id' : ?0 }")
    void updateProfileImageId(String userId, String imageId);


    @Query("{ '_id' : ?0 }, { 'favoriteCourses' : 1 }")
    Optional<UserEntity> findUserWithFavorites(String email);
}
