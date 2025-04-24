package Pfe_Education.mongo.service.schoolyear;

import Pfe_Education.mongo.Entities.SchoolYear;
import java.util.List;
import java.util.Optional;

public interface SchoolYearService {
    SchoolYear addSchoolYear(SchoolYear schoolYear);
    List<SchoolYear> getAllSchoolYears();
    Optional<SchoolYear> getSchoolYearById(String id);
    SchoolYear updateSchoolYear(String id, SchoolYear schoolYear);
    void deleteSchoolYear(String id);
}
