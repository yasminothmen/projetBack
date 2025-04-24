package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.Entities.WorkshopEntity;
import Pfe_Education.mongo.service.cour.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workshops")
@CrossOrigin(origins = "*")

public class WorkshopController {

    @Autowired
    private WorkshopService workshopService;

    @PostMapping("/add")
    public ResponseEntity<?> addWorkshop(@RequestBody WorkshopEntity workshop) {
        System.out.println("Received: " + workshop); // Log ici
        WorkshopEntity savedWorkshop = workshopService.addWorkshop(workshop);
        return new ResponseEntity<>(savedWorkshop, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public List<WorkshopEntity> getAllWorkshops() {
        return workshopService.getAllWorkshops();
    }

    @GetMapping("/{id}")
    public Optional<WorkshopEntity> getWorkshopById(@PathVariable String id) {
        return workshopService.getWorkshopById(id);
    }

    @PutMapping("/update/{id}")
    public WorkshopEntity updateWorkshop(@PathVariable String id, @RequestBody WorkshopEntity workshop) {
        return workshopService.updateWorkshop(id, workshop);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteWorkshop(@PathVariable String id) {
        workshopService.deleteWorkshop(id);
        return "Workshop supprimé avec succès !";
    }


    @GetMapping("/{id}/title")
    public String getWorkshopTitle(@PathVariable String id) {
        return workshopService.getWorkshopTitleById(id);
    }
}
