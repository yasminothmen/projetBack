package Pfe_Education.mongo.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "chatRooms") // Nom de la collection pour ChatRoom dans MongoDB
public class ChatRoomEntity {

    @Id
    private String id;

    private String nom_gr;  // Nom du groupe ou identifiant du groupe
    private String nom;     // Nom de la chat room
    private List<UserEntity> membres; // Liste des membres de type User

    // Constructeur vide
    public ChatRoomEntity() {
    }

    // Constructeur avec paramètres
    public ChatRoomEntity(String id, String nom_gr, String nom, List<UserEntity> membres) {
        this.id = id;
        this.nom_gr = nom_gr; // Assignation du nom du groupe à l'attribut
        this.nom = nom;
        this.membres = membres;
    }
}
