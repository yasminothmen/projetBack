package Pfe_Education.mongo.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

import java.util.List;

@Document(collection = "subjects") // Collection MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subject {

    @Id
    private String id;

    private String name;
    private List<String> level;
    private String nombreHeures;
    private String type;
}
