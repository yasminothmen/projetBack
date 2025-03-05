package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.entities.WorkshopEntity;
import Pfe_Education.mongo.service.workshop.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workshops")
public class WorkshopController {

    @Autowired
    private WorkshopService workshopService;

    @PostMapping("/add")
    public WorkshopEntity addWorkshop(@RequestBody WorkshopEntity workshop) {
        return workshopService.addWorkshop(workshop);
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
}
