package Pfe_Education.mongo.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;


@Document(collection = "messages_images")
public class MessageImage extends MessageEntity {

    private String urlImage; // URL de l'image

    // Constructeur par défaut
    public MessageImage() {
        super(); // Appel du constructeur de la classe parente
    }

    // Constructeur avec paramètres
    public MessageImage(String expediteur, Date dateEnvoi, String contenu, String urlImage) {
        super(expediteur, dateEnvoi, contenu); // Appel du constructeur de la classe parente
        this.urlImage = urlImage; // Assignation de l'URL de l'image
    }

    // Getter et Setter
    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
