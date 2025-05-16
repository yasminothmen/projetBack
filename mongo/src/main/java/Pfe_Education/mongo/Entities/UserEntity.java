package Pfe_Education.mongo.Entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserEntity {

    @Id
    private String id; // MongoDB utilise un ObjectId (String) comme ID

    @Size(max = 10, message = "Le prénom ne doit pas dépasser 10 caractères")
    private String firstname;
    private String firebaseUid;
    private String lastname;

    @NotNull
    @Email(message = "Email invalide")
    private String email;
    @NotNull
    private String password;
    private String confirmPassword;
    @NotNull
    private String username;
    private String role;
    private String profileImageId;
    @DBRef
    private List<Cour> favoriteCourses = new ArrayList<>();

}