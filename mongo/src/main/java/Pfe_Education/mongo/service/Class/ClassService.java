package Pfe_Education.mongo.service.Class;

import Pfe_Education.mongo.Entities.ClassEntity;
import java.util.List;

public interface ClassService {
    ClassEntity addClass(ClassEntity classEntity); // To add a class
    List<ClassEntity> getAllClasses(); // To get all classes
    ClassEntity updateClass(String id, ClassEntity classEntity); // To update a class
    void deleteClass(String id); // To delete a class
}
