package Pfe_Education.mongo.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "subscriptions")
public class Subscription {

    @Id
    private String id;

    @DBRef
    private ClassEntity classe;
    // Référence à une classe

    public Subscription() {}

    public Subscription(ClassEntity classe) {
        this.classe = classe;
    }
}
