package Pfe_Education.mongo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "admins") // Collection spécifique pour les Admins
public class AdminEntity extends UserEntity {

    //private String role = "ADMIN"; // Définit le rôle spécifique

    public AdminEntity() {
        super();
        //this.role = "ADMIN";
    }
}
