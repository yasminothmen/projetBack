package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.Entities.Schedule;
import Pfe_Education.mongo.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedules")
@CrossOrigin("*")

public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // Créer un emploi du temps
    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        Schedule savedSchedule = scheduleService.createSchedule(schedule);
        return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
    }

    // Récupérer l'emploi du temps pour une classe spécifique
    @GetMapping("/{className}")
    public ResponseEntity<List<Schedule>> getScheduleForClass(@PathVariable String className) {
        List<Schedule> schedules = scheduleService.getScheduleForClass(className);
        if (schedules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(schedules);
    }

    // Récupérer l'emploi du temps pour un niveau spécifique
    @GetMapping("/level/{level}")
    public ResponseEntity<List<Schedule>> getScheduleByLevel(@PathVariable String level) {
        List<Schedule> schedules = scheduleService.getScheduleByLevel(level);
        if (schedules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(schedules);
    }

    // Mettre à jour un emploi du temps existant
    @PutMapping("/update/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable String id, @RequestBody Schedule schedule) {
        Schedule updatedSchedule = scheduleService.updateSchedule(id, schedule);
        if (updatedSchedule != null) {
            return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un emploi du temps
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable String id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/teacher/{teacherName}")
    public ResponseEntity<List<Map<String, Object>>> getScheduleByTeacher(
            @PathVariable String teacherName) {

        // 1. Récupérer les Schedule (pas Class)
        List<Schedule> schedules = scheduleService.getScheduleForTeacher(teacherName);

        // 2. Transformer en liste de Map
        List<Map<String, Object>> response = schedules.stream()
                .flatMap(schedule -> schedule.getSessions().stream()
                        .filter(session -> session.getTeacher().equalsIgnoreCase(teacherName))
                        .map(session -> {
                            Map<String, Object> sessionMap = new HashMap<>();
                            sessionMap.put("className", schedule.getClassName());
                            sessionMap.put("level", schedule.getLevel());
                            sessionMap.put("day", session.getDay());
                            sessionMap.put("time", session.getTime());
                            sessionMap.put("subject", session.getSubject());
                            return sessionMap;
                        })
                )
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/teacher/{teacherName}/by-day")
    public ResponseEntity<Map<String, List<Schedule.Session>>> getTeacherScheduleGroupedByDay(
            @PathVariable String teacherName) {
        return ResponseEntity.ok(scheduleService.getTeacherScheduleGroupedByDay(teacherName));
    }
}