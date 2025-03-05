package Pfe_Education.mongo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "exercices")
public class Exercice extends WorkshopEntity {



    private String titreEx;
    private String descriptionEx;
    private String niveauDifficulte;
    private Date dateCreation;
    private Date dateSoumission;
    private String typeEx; // OCM / Jeux / PB
    private double note;

    public Exercice() {
        super();
    }

    public Exercice(String nom, int duree, String objectif, String urlImage, UserEntity enseignant,
                    String titreEx, String descriptionEx, String niveauDifficulte, Date dateCreation,
                    Date dateSoumission, String typeEx, double note) {
        super(nom, duree, objectif, urlImage, enseignant); // Appelle le constructeur de Workshop
        this.titreEx = titreEx;
        this.descriptionEx = descriptionEx;
        this.niveauDifficulte = niveauDifficulte;
        this.dateCreation = dateCreation;
        this.dateSoumission = dateSoumission;
        this.typeEx = typeEx;
        this.note = note;
    }
}
