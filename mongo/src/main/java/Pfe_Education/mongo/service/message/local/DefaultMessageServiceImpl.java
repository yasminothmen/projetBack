package Pfe_Education.mongo.service.message.local;

import Pfe_Education.mongo.entities.MessageEntity;
import Pfe_Education.mongo.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DefaultMessageServiceImpl implements MessageService {

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
}
