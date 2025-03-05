package Pfe_Education.mongo.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
public class MessageEntity {

    @Id
    private String id;

    private String expediteur;
    private Date dateEnvoi;
    //private String contenu;

    public MessageEntity() {}

    public MessageEntity(String expediteur, Date dateEnvoi, String contenu) {
        this.expediteur = expediteur;
        this.dateEnvoi = dateEnvoi;
        //this.contenu = contenu;
    }
}
