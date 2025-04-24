package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.Entities.Teacher;
import Pfe_Education.mongo.Entities.UserEntity;
import Pfe_Education.mongo.service.user.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/by-email/{email}")
    public ResponseEntity<String> getFirstnameByEmail(@PathVariable String email) {
        // Utilisez l'instance injectée userService au lieu d'appeler statiquement
        UserEntity user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return ResponseEntity.ok(user.getFirstname());
    }

    @GetMapping("/findlastname/{email}")
    public ResponseEntity<String> getLastnameByEmail(@PathVariable String email) {
        // Utilisez l'instance injectée userService au lieu d'appeler statiquement
        UserEntity user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return ResponseEntity.ok(user.getLastname());
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

    // Récupérer uniquement les étudiants
    @GetMapping("/students")
    public List<UserEntity> getStudents() {
        return userService.getStudents();
    }

    // Récupérer uniquement les enseignants
    @GetMapping("/teachers")
    public List<UserEntity> getTeachers() {
        return userService.getTeachers();
    }

    // Ajouter un étudiant (rôle "student" automatiquement affecté)
    @PostMapping("/add-student")
    public UserEntity addStudent(@RequestBody UserEntity user) {
        user.setRole("student"); // Attribuer le rôle "student"
        return userService.addUser(user); // Appel au service pour enregistrer l'utilisateur
    }

    // Ajouter un enseignant (rôle "teacher" automatiquement affecté)
    @PostMapping("/add-teacher")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        // Assurez-vous que le rôle "teacher" est bien affecté
        if (teacher.getRole() == null || teacher.getRole().isEmpty()) {
            teacher.setRole("teacher");  // Si le rôle est null ou vide, on lui assigne "teacher"
        }

        // Sauvegarde de l'enseignant avec le rôle défini
        return userService.addTeacher(teacher);
    }


}