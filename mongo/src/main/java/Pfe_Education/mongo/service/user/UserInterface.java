package Pfe_Education.mongo.service.user;

import Pfe_Education.mongo.Entities.Teacher;
import Pfe_Education.mongo.Entities.UserEntity;

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
    List<UserEntity> getStudents();
    List<UserEntity> getTeachers();
    Optional<UserEntity> getUserByUsername(String username);
    Teacher addTeacher(Teacher teacher);
    Optional<UserEntity> findByEmail(String email);
    UserEntity updateProfileImageId(String userId, String imageId);
    Optional<UserEntity> findById(String userId);
    Optional<UserEntity> findProfileImageIdById(String userId);

    Optional<UserEntity> getUserWithProfileImage(String email);
}
