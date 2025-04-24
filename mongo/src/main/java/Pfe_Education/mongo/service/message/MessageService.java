package Pfe_Education.mongo.service.message;

import Pfe_Education.mongo.Entities.MessageEntity;
import java.util.List;

public interface MessageService {
    MessageEntity addMessage(MessageEntity message);
    List<MessageEntity> getAllMessages();
    void deleteMessage(String id);


}
