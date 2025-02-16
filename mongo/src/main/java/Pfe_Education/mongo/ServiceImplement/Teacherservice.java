package Pfe_Education.mongo.ServiceImplement;

import Pfe_Education.mongo.Entities.TeacherEntity;
import Pfe_Education.mongo.repository.TeacherRepo;
import Pfe_Education.mongo.Service.TeacherInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Teacherservice implements TeacherInterface {

    @Autowired
    private TeacherRepo teacherRepo;

    @Override
    public TeacherEntity addTeacher(TeacherEntity teacher) {
        return teacherRepo.save(teacher);
    }

    @Override
    public void deleteTeacher(String id) {
        teacherRepo.deleteById(id);
    }

    @Override
    public List<TeacherEntity> addListTeachers(List<TeacherEntity> listTeachers) {
        return teacherRepo.saveAll(listTeachers);
    }

    @Override
    public String addTeacherWithPasswordCheck(TeacherEntity teacher) {
        if (teacher.getPassword().equals(teacher.getConfirmPassword())) {
            teacherRepo.save(teacher);
            return "Teacher added successfully!";
        } else {
            return "Password and confirm password do not match!";
        }
    }

    @Override
    public String addTeacherWithUsernameCheck(TeacherEntity teacher) {
        if (teacherRepo.existsByUsername(teacher.getUsername())) {
            return "Username already exists!";
        } else {
            teacherRepo.save(teacher);
            return "Teacher added successfully!";
        }
    }

    @Override
    public TeacherEntity updateTeacher(String id, TeacherEntity teacher) {
        TeacherEntity existingTeacher = teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        existingTeacher.setFirstname(teacher.getFirstname());
        existingTeacher.setLastname(teacher.getLastname());
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setPassword(teacher.getPassword());
        existingTeacher.setUsername(teacher.getUsername());

        return teacherRepo.save(existingTeacher);
    }

    @Override
    public List<TeacherEntity> getAllTeachers() {
        return teacherRepo.findAll();
    }

    @Override
    public TeacherEntity getTeacher(String id) {
        return teacherRepo.findById(id).orElse(null);
    }

    @Override
    public TeacherEntity getTeacherByUsername(String username) {
        return teacherRepo.findByUsername(username);
    }
}