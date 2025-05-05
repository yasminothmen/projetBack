package Pfe_Education.mongo.Entities;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "Discussions")
public class Conversation {
    @Id
    private String id;
    private String nom;
    private List<UserEntity> members;
    private List<MessageEntity> messages;
}
