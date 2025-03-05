package Pfe_Education.mongo.service.subject.local;

import Pfe_Education.mongo.entities.Subject;
import Pfe_Education.mongo.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultSubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(String id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(String id, Subject subject) {
        Optional<Subject> existingSubject = subjectRepository.findById(id);
        if (existingSubject.isPresent()) {
            Subject updatedSubject = existingSubject.get();
            updatedSubject.setName(subject.getName());
            updatedSubject.setLevel(subject.getLevel());
            updatedSubject.setType(subject.getType());
            return subjectRepository.save(updatedSubject);
        }
        return null;
    }

    @Override
    public void deleteSubject(String id) {
        subjectRepository.deleteById(id);
    }
}
