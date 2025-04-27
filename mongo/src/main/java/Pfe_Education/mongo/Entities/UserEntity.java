package Pfe_Education.mongo.Entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserEntity {

    @Id
    private String id; // MongoDB utilise un ObjectId (String) comme ID

    @Size(max = 10, message = "Le prénom ne doit pas dépasser 10 caractères")
    private String firstname;

    private String lastname;

    @NotNull
    @Email(message = "Email invalide")
    private String email;

    @NotNull
    private String password;

    private String confirmPassword;

    @NotNull
    private String username;

    private String role; // Ajout du champ rôle (student, teacher, admin)
    private String profileImageId; // Référence à l'image de son profile


}
