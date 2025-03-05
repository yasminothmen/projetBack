package Pfe_Education.mongo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "profs") // Collection spécifique pour les professeurs
public class Teacher extends UserEntity {

    public Teacher() {
        super();
    }
}
