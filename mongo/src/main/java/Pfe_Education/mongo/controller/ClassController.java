package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.entities.ClassEntity; // Correct import for the ClassEntity
import Pfe_Education.mongo.service.Class.ClassService; // Correct import for the ClassService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes") // Base URL for this controller
public class ClassController {

    @Autowired
    private ClassService classService; // Autowiring the ClassService to interact with class-related operations

    // Create a new class
    @PostMapping
    public ResponseEntity<ClassEntity> createClass(@RequestBody ClassEntity classEntity) {
        ClassEntity createdClass = classService.addClass(classEntity); // Call service to save class
        return ResponseEntity.ok(createdClass); // Return the created class in response
    }

    // Get all classes
    @GetMapping
    public ResponseEntity<List<ClassEntity>> getAllClasses() {
        List<ClassEntity> classes = classService.getAllClasses(); // Get all classes from the service
        return ResponseEntity.ok(classes); // Return the list of classes in response
    }

    // Update class by ID
    @PutMapping("/{id}")
    public ResponseEntity<ClassEntity> updateClass(@PathVariable String id, @RequestBody ClassEntity classEntity) {
        ClassEntity updatedClass = classService.updateClass(id, classEntity); // Call service to update class
        if (updatedClass != null) {
            return ResponseEntity.ok(updatedClass); // Return the updated class
        }
        return ResponseEntity.notFound().build(); // Return 404 if class not found
    }

    // Delete class by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable String id) {
        classService.deleteClass(id); // Call service to delete class by ID
        return ResponseEntity.noContent().build(); // Return a 204 No Content status
    }
}
