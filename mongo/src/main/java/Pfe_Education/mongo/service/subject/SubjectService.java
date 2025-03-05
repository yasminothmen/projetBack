package Pfe_Education.mongo.service.subject;

import Pfe_Education.mongo.entities.Subject;
import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();
    Subject getSubjectById(String id);
    Subject createSubject(Subject subject);
    Subject updateSubject(String id, Subject subject);
    void deleteSubject(String id);
}
