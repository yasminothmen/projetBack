package Pfe_Education.mongo.Service;

import Pfe_Education.mongo.Entities.TeacherEntity;

import java.util.List;

public interface TeacherInterface {
    TeacherEntity addTeacher(TeacherEntity teacher);
    void deleteTeacher(String id); // Utilisation de String pour l'ID Mongo
    List<TeacherEntity> addListTeachers(List<TeacherEntity> listTeachers);
    String addTeacherWithPasswordCheck(TeacherEntity teacher);
    String addTeacherWithUsernameCheck(TeacherEntity teacher);
    TeacherEntity updateTeacher(String id, TeacherEntity teacher);
    List<TeacherEntity> getAllTeachers();
    TeacherEntity getTeacher(String id);
    TeacherEntity getTeacherByUsername(String username);
}