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
    private String receiver;
    private String chatRoomId;
    private WebsocketType type;

}
