package Pfe_Education.mongo.service.schoolyear.local;

import Pfe_Education.mongo.Entities.SchoolYear;
import Pfe_Education.mongo.service.schoolyear.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultSchoolYearServiceImpl implements SchoolYearService {

    @Autowired
    private SchoolYearRepository schoolYearRepository;

    @Override
    public SchoolYear addSchoolYear(SchoolYear schoolYear) {
        try {
            return schoolYearRepository.save(schoolYear);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("The school year with the name '" + schoolYear.getName() + "' already exists.");
        }
    }

    @Override
    public List<SchoolYear> getAllSchoolYears() {
        return schoolYearRepository.findAll();
    }

    @Override
    public Optional<SchoolYear> getSchoolYearById(String id) {
        return schoolYearRepository.findById(id);
    }

    @Override
    public SchoolYear updateSchoolYear(String id, SchoolYear schoolYear) {
        if (schoolYearRepository.existsById(id)) {
            schoolYear.setId(id);
            return schoolYearRepository.save(schoolYear);
        }
        return null;
    }

    @Override
    public void deleteSchoolYear(String id) {
        schoolYearRepository.deleteById(id);
    }
}
