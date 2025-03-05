package Pfe_Education.mongo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "schedules")
public class Schedule {
    @Id
    private String id;
    private String name;

    // Constructeur par défaut
    public Schedule() {}

    // Constructeur avec paramètres
    public Schedule(String name) {
        this.name = name;
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
