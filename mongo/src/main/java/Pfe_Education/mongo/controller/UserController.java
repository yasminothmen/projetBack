package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.entities.UserEntity;
import Pfe_Education.mongo.service.user.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user") // Utilisation de "user" pour l'API des utilisateurs
public class UserController {

    @Autowired
    private UserInterface userService; // Injection correcte du service

    // Ajouter un User
    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity user) {
        return userService.addUser(user); // Utilisation de l'instance injectée
    }

    // Supprimer un User par ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    // Ajouter une liste de Users
    @PostMapping("/List")
    public List<UserEntity> addListUsers(@RequestBody List<UserEntity> users) {
        return userService.addListUsers(users);
    }

    // Ajouter un User avec vérification du mot de passe
    @PostMapping("/with-password-check")
    public String addUserWithPasswordCheck(@RequestBody UserEntity user) {
        return userService.addUserWithPasswordCheck(user);
    }

    // Ajouter un User avec vérification du nom d'utilisateur
    @PostMapping("/with-username-check")
    public String addUserWithUsernameCheck(@RequestBody UserEntity user) {
        return userService.addUserWithUsernameCheck(user);
    }

    // Mettre à jour un User par ID
    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable String id, @RequestBody UserEntity user) {
        return userService.updateUser(id, user);
    }

    // Obtenir tous les Users
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    // Obtenir un User par ID
    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable String id) {
        return userService.getUser(id);
    }

    // Obtenir un User par nom d'utilisateur
    @GetMapping("/username/{username}")
    public Optional<UserEntity> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
