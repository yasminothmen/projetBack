package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.Entities.Cour;
import Pfe_Education.mongo.service.cour.CourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workshops")
@CrossOrigin(origins = "*")

public class CourController {

    @Autowired
    private CourService courService;

    @PostMapping("/add")
    public ResponseEntity<?> addWorkshop(@RequestBody Cour workshop) {
        System.out.println("Received: " + workshop); // Log ici
        Cour savedWorkshop = courService.addWorkshop(workshop);
        return new ResponseEntity<>(savedWorkshop, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public List<Cour> getAllWorkshops() {
        return courService.getAllWorkshops();
    }

    @GetMapping("/{id}")
    public Optional<Cour> getWorkshopById(@PathVariable String id) {
        return courService.getWorkshopById(id);
    }

    @PutMapping("/update/{id}")
    public Cour updateWorkshop(@PathVariable String id, @RequestBody Cour workshop) {
        return courService.updateWorkshop(id, workshop);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteWorkshop(@PathVariable String id) {
        courService.deleteWorkshop(id);
        return "Workshop supprimé avec succès !";
    }


    @GetMapping("/{id}/title")
    public String getWorkshopTitle(@PathVariable String id) {
        return courService.getWorkshopTitleById(id);
    }
}
