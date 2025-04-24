package Pfe_Education.mongo.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "schedules")
public class Schedule {
    @Id
    private String id;

    @Field("class_name")
    private String className;

    private String level;

    private List<Session> sessions;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Session {
        private String day;
        private String time;
        private String teacher;
        private String subject;
    }
}