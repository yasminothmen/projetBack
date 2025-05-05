package Pfe_Education.mongo.Entities;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String content;
    private String sender;
    private String reciever;
    private String chatRoomId;
    private MessageType type;

}
