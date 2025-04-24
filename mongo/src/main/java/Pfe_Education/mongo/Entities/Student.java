package Pfe_Education.mongo.Entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "eleves") // Collection spécifique pour les élèves
public class Student extends UserEntity {
    public Student() {
        super();
    }
}
