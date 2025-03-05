package Pfe_Education.mongo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "videos")
public class Video extends WorkshopEntity {

    private String urlVideo;
    private double tailleVideo;
    private String format;
    private String quantiteVideo;
    private int dureeVid;
    public Video() {
        super();
    }

    public Video(String nom, int duree, String objectif, String urlImage, UserEntity enseignant,
                 String urlVideo, double tailleVideo, String format, String quantiteVideo) {
        super(nom, duree, objectif, urlImage, enseignant);
        this.urlVideo = urlVideo;
        this.tailleVideo = tailleVideo;
        this.format = format;
        this.quantiteVideo = quantiteVideo;
        this.dureeVid = dureeVid;
    }


    public int getDureeInMinutes() {
        return dureeVid / 60;
    }
}
