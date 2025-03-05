package Pfe_Education.mongo.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "workshops")
public class WorkshopEntity {

    @Id
    private String id;

    private String nom;
    private int duree;
    private String objectif;
    private String urlImage;

    @DBRef
    private UserEntity enseignant;

    public WorkshopEntity() {
    }

    public WorkshopEntity(String nom, int duree, String objectif, String urlImage, UserEntity enseignant) {
        this.nom = nom;
        this.duree = duree;
        this.objectif = objectif;
        this.urlImage = urlImage;
        this.enseignant = enseignant;
    }
}
