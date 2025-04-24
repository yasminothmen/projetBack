package Pfe_Education.mongo.service.user;

import Pfe_Education.mongo.Entities.Teacher;
import Pfe_Education.mongo.Entities.UserEntity;
import Pfe_Education.mongo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultUserServiceImpliment implements UserInterface {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserEntity addUser(UserEntity user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<UserEntity> addListUsers(List<UserEntity> listUsers) {
        return userRepo.saveAll(listUsers);
    }

    @Override
    public String addUserWithPasswordCheck(UserEntity user) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            userRepo.save(user);
            return "User added successfully!";
        } else {
            return "Password and confirm password do not match!";
        }
    }

    @Override
    public String addUserWithUsernameCheck(UserEntity user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            return "Username already exists!";
        } else {
            userRepo.save(user);
            return "User added successfully!";
        }
    }

    @Override
    public UserEntity updateUser(String id, UserEntity user) {
        UserEntity existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setUsername(user.getUsername());

        return userRepo.save(existingUser);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity getUser(String id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<UserEntity> getStudents() {
        return userRepo.findAll()  // Utilisation de l'instance userRepo ici
                .stream()
                .filter(user -> "student".equalsIgnoreCase(user.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserEntity> getTeachers() {
        return userRepo.findAll()  // Utilisation de l'instance userRepo ici
                .stream()
                .filter(user -> "teacher".equalsIgnoreCase(user.getRole()))
                .collect(Collectors.toList());
    }


    @Override
    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return userRepo.save(teacher);
    }
    @Override // Implémentation de la méthode d'interface
    public Optional<UserEntity> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

}
