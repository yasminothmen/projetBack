package Pfe_Education.mongo.service.schedule.local;

import Pfe_Education.mongo.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Pfe_Education.mongo.entities.Schedule;


import java.util.List;

import java.util.Optional;

@Service
public class DefaultScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> getScheduleById(String id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(String id) {
        scheduleRepository.deleteById(id);
    }
}
