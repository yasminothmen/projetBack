package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.Entities.SchoolYear;
import Pfe_Education.mongo.service.schoolyear.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/school-years")
public class SchoolYearController {

    @Autowired
    private SchoolYearService schoolYearService;

    @PostMapping
    public ResponseEntity<?> createSchoolYear(@RequestBody SchoolYear schoolYear) {
        try {
            SchoolYear newSchoolYear = schoolYearService.addSchoolYear(schoolYear);
            return new ResponseEntity<>(newSchoolYear, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<SchoolYear> getAllSchoolYears() {
        return schoolYearService.getAllSchoolYears();
    }

    @GetMapping("/{id}")
    public Optional<SchoolYear> getSchoolYearById(@PathVariable String id) {
        return schoolYearService.getSchoolYearById(id);
    }

    @PutMapping("/{id}")
    public SchoolYear updateSchoolYear(@PathVariable String id, @RequestBody SchoolYear schoolYear) {
        return schoolYearService.updateSchoolYear(id, schoolYear);
    }

    @DeleteMapping("/{id}")
    public void deleteSchoolYear(@PathVariable String id) {
        schoolYearService.deleteSchoolYear(id);
    }
}
