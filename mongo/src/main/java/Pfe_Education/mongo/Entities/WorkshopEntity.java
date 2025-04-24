package Pfe_Education.mongo.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "workshops")
public class WorkshopEntity {
    @Id
    private String id;
    private String titre;
    private String description;
    private String instructor;
    private String matiere;
    private String classe;
    private String imagePath;
    private List<Lesson> lessons;
    private Exercice exercice;
    private String rating = "1.0";

    private boolean bookmarked ;
    private String workshopDuration; // Durée totale du workshop
    public WorkshopEntity() {}

    public WorkshopEntity(String titre, String description, String instructor,
                          String matiere, String classe, String imagePath,
                          List<Lesson> lessons, Exercice exercice,String workshopDuration,boolean bookmarked ) {
        this.titre = titre;
        this.description = description;
        this.instructor = instructor;
        this.bookmarked = bookmarked;
        this.matiere = matiere;
        this.classe = classe;
        this.imagePath = imagePath;
        this.lessons = lessons;
        this.exercice = exercice;
        this.workshopDuration = workshopDuration;
    }

    @Data
    public static class Lesson {
        private String titre;
        private String lessonUrl;
        private String lessonDuration; // Durée de la leçon

        public Lesson() {}
        public Lesson(String titre, String lessonUrl,String lessonDuration) {
            this.titre = titre;
            this.lessonUrl = lessonUrl;
            this.lessonDuration = lessonDuration;

        }
    }

    @Data
    public static class Exercice {
        private String titre;
        private String exerciceUrl;

        public Exercice() {}
        public Exercice(String titre, String exerciceUrl) {
            this.titre = titre;
            this.exerciceUrl = exerciceUrl;
        }
    }
}