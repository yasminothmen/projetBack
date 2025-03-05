package Pfe_Education.mongo.service.user;

import Pfe_Education.mongo.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserInterface {
    UserEntity addUser(UserEntity user);
    void deleteUser(String id); // Utilisation de String pour l'ID Mongo
    List<UserEntity> addListUsers(List<UserEntity> listUsers);
    String addUserWithPasswordCheck(UserEntity user);
    String addUserWithUsernameCheck(UserEntity user);
    UserEntity updateUser(String id, UserEntity user);
    List<UserEntity> getAllUsers();
    UserEntity getUser(String id);
    Optional<UserEntity> getUserByUsername(String username);
}
