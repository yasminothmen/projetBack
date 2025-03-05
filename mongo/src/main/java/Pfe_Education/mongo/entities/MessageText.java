package Pfe_Education.mongo.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;


@Document(collection = "messages_textes")
public class MessageText extends MessageEntity {

    private String contenu;

    // Constructeur par défaut
    public MessageText() {
        super(); // Appel du constructeur de la classe parente
    }

    // Constructeur avec paramètres
    public MessageText(String expediteur, Date dateEnvoi, String contenu) {
        super(expediteur, dateEnvoi, contenu); // Appel du constructeur de la classe parente
        this.contenu = contenu; // Assignation du contenu
    }

    // Getter et Setter
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
