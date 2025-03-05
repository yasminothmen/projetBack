package Pfe_Education.mongo.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;


@Document(collection = "messages_vocaux")
public class MessageVocal extends MessageEntity {

    private String urlAudio; // URL du fichier audio

    // Constructeurs
    public MessageVocal() {
        super(); // Appel du constructeur de la classe parente
    }

    public MessageVocal(String expediteur, Date dateEnvoi, String contenu, String urlAudio) {
        super(expediteur, dateEnvoi, contenu); // Appel du constructeur de la classe parente
        this.urlAudio = urlAudio;
    }

    // Getters et setters
    public String getUrlAudio() {
        return urlAudio;
    }

    public void setUrlAudio(String urlAudio) {
        this.urlAudio = urlAudio;
    }
}
