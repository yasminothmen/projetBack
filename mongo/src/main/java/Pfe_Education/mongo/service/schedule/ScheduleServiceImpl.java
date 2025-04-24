package Pfe_Education.mongo.service.schedule;

import Pfe_Education.mongo.Entities.Schedule;
import Pfe_Education.mongo.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getScheduleForClass(String className) {
        return scheduleRepository.findByClassName(className);
    }

    @Override
    public List<Schedule> getScheduleByLevel(String level) {
        return scheduleRepository.findByLevel(level);
    }

    @Override
    public Schedule updateSchedule(String id, Schedule schedule) {
        Optional<Schedule> existingSchedule = scheduleRepository.findById(id);
        if (existingSchedule.isPresent()) {
            Schedule updatedSchedule = existingSchedule.get();
            updatedSchedule.setClassName(schedule.getClassName());
            updatedSchedule.setLevel(schedule.getLevel());
            updatedSchedule.setSessions(schedule.getSessions());
            return scheduleRepository.save(updatedSchedule);
        } else {
            return null; // Ou gérer l'erreur comme vous le souhaitez
        }
    }

    @Override
    public void deleteSchedule(String id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<Schedule> getScheduleForTeacher(String teacherName) {
        return scheduleRepository.findByTeacher(teacherName);
    }

    @Override
    public Map<String, List<Schedule.Session>> getTeacherScheduleGroupedByDay(String teacherName) {
        List<Schedule> schedules = getScheduleForTeacher(teacherName);

        return schedules.stream()
                .flatMap(schedule -> schedule.getSessions().stream()
                        .filter(session -> teacherName.equalsIgnoreCase(session.getTeacher()))
                )
                .collect(Collectors.groupingBy(Schedule.Session::getDay));
    }
}
