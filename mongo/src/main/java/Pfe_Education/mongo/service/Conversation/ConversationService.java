package Pfe_Education.mongo.service.Conversation;

import Pfe_Education.mongo.Entities.Conversation;
import Pfe_Education.mongo.Entities.MessageEntity;

import java.util.List;

public interface ConversationService {
    Conversation createChatRoom(Conversation chatRoom);
    List<Conversation> getAllChatRooms();
    Conversation getChatRoomById(String id);
    void deleteChatRoom(String id);
    List<MessageEntity> getMessagesByChatRoomId(String chatRoomId);

}
