package Pfe_Education.mongo.service.message;

import Pfe_Education.mongo.Entities.MessageEntity;
import Pfe_Education.mongo.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public MessageEntity addMessage(MessageEntity message) {
        return messageRepository.save(message);
    }

    @Override
    public List<MessageEntity> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public void deleteMessage(String id) {
        messageRepository.deleteById(id);
    }

    @Override
    public List<MessageEntity> getChatRoomHistory(String chatroomId) {
        return messageRepository.findByChatroomId(chatroomId)
                .stream()
                .sorted(Comparator.comparing(MessageEntity::getDate))
                .collect(Collectors.toList());    }
    @Override
    public List<MessageEntity> getDirectMessagesHistory(String user1Id, String user2Id) {
        return messageRepository.findDirectMessages(user1Id, user2Id)
                .stream()
                .sorted(Comparator.comparing(MessageEntity::getDate))
                .collect(Collectors.toList());    }
}
