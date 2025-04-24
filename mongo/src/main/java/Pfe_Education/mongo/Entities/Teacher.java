package Pfe_Education.mongo.Entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "profs") // Collection spécifique pour les professeurs
public class Teacher extends UserEntity {

    private List<String> matieresEnseignees;  // Liste des matières enseignées


    public Teacher() {
        super();
    }
}
