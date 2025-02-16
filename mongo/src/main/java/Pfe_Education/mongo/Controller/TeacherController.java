package Pfe_Education.mongo.Controller;

import Pfe_Education.mongo.Service.TeacherInterface;
import Pfe_Education.mongo.Entities.TeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherInterface teacherService;

    // Ajouter un enseignant
    @PostMapping
    public TeacherEntity addTeacher(@RequestBody TeacherEntity teacher) {
        return teacherService.addTeacher(teacher);
    }

    // Supprimer un enseignant par ID
    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable String id) {
        teacherService.deleteTeacher(id);
    }

    // Ajouter une liste d'enseignants
    @PostMapping("/bulk")
    public List<TeacherEntity> addListTeachers(@RequestBody List<TeacherEntity> teachers) {
        return teacherService.addListTeachers(teachers);
    }

    // Ajouter un enseignant avec vérification du mot de passe
    @PostMapping("/with-password-check")
    public String addTeacherWithPasswordCheck(@RequestBody TeacherEntity teacher) {
        return teacherService.addTeacherWithPasswordCheck(teacher);
    }

    // Ajouter un enseignant avec vérification du nom d'utilisateur
    @PostMapping("/with-username-check")
    public String addTeacherWithUsernameCheck(@RequestBody TeacherEntity teacher) {
        return teacherService.addTeacherWithUsernameCheck(teacher);
    }

    // Mettre à jour un enseignant par ID
    @PutMapping("/{id}")
    public TeacherEntity updateTeacher(@PathVariable String id, @RequestBody TeacherEntity teacher) {
        return teacherService.updateTeacher(id, teacher);
    }

    // Obtenir tous les enseignants
    @GetMapping
    public List<TeacherEntity> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    // Obtenir un enseignant par ID
    @GetMapping("/{id}")
    public TeacherEntity getTeacherById(@PathVariable String id) {
        return teacherService.getTeacher(id);
    }

    // Obtenir un enseignant par nom d'utilisateur
    @GetMapping("/username/{username}")
    public TeacherEntity getTeacherByUsername(@PathVariable String username) {
        return teacherService.getTeacherByUsername(username);
    }


}