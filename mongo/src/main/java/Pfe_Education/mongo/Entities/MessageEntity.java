package Pfe_Education.mongo.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Document(collection = "messages")
public class MessageEntity {
    @Id
    private String id;
    private String content;
    private String senderId;
    private WebsocketType type;
    private String recieverId;
    private LocalDateTime date;
    private String chatroomId;
}
