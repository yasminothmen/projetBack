package Pfe_Education.mongo.service.schedule;

import Pfe_Education.mongo.entities.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<Schedule> getAllSchedules();
    Optional<Schedule> getScheduleById(String id);
    Schedule createSchedule(Schedule schedule);
    void deleteSchedule(String id);
}
