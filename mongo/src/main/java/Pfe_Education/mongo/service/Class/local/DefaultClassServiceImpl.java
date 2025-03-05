package Pfe_Education.mongo.service.Class.local;

import Pfe_Education.mongo.entities.ClassEntity; // Correct import for the ClassEntity
import Pfe_Education.mongo.service.Class.ClassService; // Correct import for the ClassService interface
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository; // Autowiring ClassRepository to handle CRUD operations

    @Override
    public ClassEntity addClass(ClassEntity classEntity) {
        return classRepository.save(classEntity); // Save the class to the repository
    }

    @Override
    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll(); // Retrieve all classes from the repository
    }

    @Override
    public ClassEntity updateClass(String id, ClassEntity classEntity) {
        if (classRepository.existsById(id)) {
            classEntity.setId(id); // Set the ID to ensure the entity is updated correctly
            return classRepository.save(classEntity); // Save the updated class entity
        }
        return null; // Return null if class with the given ID doesn't exist
    }

    @Override
    public void deleteClass(String id) {
        classRepository.deleteById(id); // Delete the class by its ID
    }
}
