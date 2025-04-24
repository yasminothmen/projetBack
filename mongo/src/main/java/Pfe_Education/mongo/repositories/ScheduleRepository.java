package Pfe_Education.mongo.repositories;

import Pfe_Education.mongo.Entities.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    // Rechercher un emploi du temps par le nom de la classe
    List<Schedule> findByClassName(String className);

    // Rechercher un emploi du temps par le niveau de la classe
    List<Schedule> findByLevel(String level);
    @Query("{ 'sessions.teacher': ?0 }")
    List<Schedule> findByTeacher(String teacherName);
}