package Pfe_Education.mongo.Entities;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@Document(collection = "Discussions")
public class Conversation {
    @Id
    private String id;
    private String nom;
    private List<UserEntity> members;
    private List<MessageEntity> messages;
    private String senderId;
    private String receiverId;
    private List<String> userIds;

}