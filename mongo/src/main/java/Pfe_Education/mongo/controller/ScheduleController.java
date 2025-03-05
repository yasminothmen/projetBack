package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.entities.Schedule;
import Pfe_Education.mongo.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // Récupérer tous les emplois du temps
    @GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    // Récupérer un emploi du temps par son ID
    @GetMapping("/{id}")
    public Optional<Schedule> getScheduleById(@PathVariable String id) {
        return scheduleService.getScheduleById(id);
    }

    // Créer un nouvel emploi du temps
    @PostMapping
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        return scheduleService.createSchedule(schedule);
    }

    // Supprimer un emploi du temps par son ID
    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable String id) {
        scheduleService.deleteSchedule(id);
    }
}
