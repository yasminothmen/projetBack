package Pfe_Education.mongo.Entities;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Data
@Document(collection = "users") // Nom de la collection MongoDB
public class TeacherEntity {
    @Id
    private String id; // MongoDB utilise un ObjectId (String) comme ID
    @Size(max = 10, message = "le nom ne doit pas depasser 10 caractère")
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

}