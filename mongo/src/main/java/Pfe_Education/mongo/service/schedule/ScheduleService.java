package Pfe_Education.mongo.service.schedule;

import Pfe_Education.mongo.Entities.Schedule;
import java.util.List;
import java.util.Map;

public interface ScheduleService {
    // Créer un emploi du temps
    Schedule createSchedule(Schedule schedule);

    // Récupérer un emploi du temps pour une classe spécifique
    List<Schedule> getScheduleForClass(String className);

    // Récupérer un emploi du temps pour un niveau spécifique
    List<Schedule> getScheduleByLevel(String level);

    // Mettre à jour un emploi du temps
    Schedule updateSchedule(String id, Schedule schedule);

    // Supprimer un emploi du temps
    void deleteSchedule(String id);
    //enseignants
    List<Schedule> getScheduleForTeacher(String teacherName);
    Map<String, List<Schedule.Session>> getTeacherScheduleGroupedByDay(String teacherName);



}