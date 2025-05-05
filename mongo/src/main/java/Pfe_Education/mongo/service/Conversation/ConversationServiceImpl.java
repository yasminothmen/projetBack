package Pfe_Education.mongo.service.Conversation;

import Pfe_Education.mongo.Entities.Conversation;
import Pfe_Education.mongo.Entities.MessageEntity;
import Pfe_Education.mongo.repositories.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public Conversation createChatRoom(Conversation chatRoom) {
        return conversationRepository.save(chatRoom);
    }

    // Récupère toutes les discussions
    @Override
    public List<Conversation> getAllChatRooms() {
        return conversationRepository.findAll();
    }

    @Override
    public Conversation getChatRoomById(String id) {
        Optional<Conversation> chatRoom = conversationRepository.findById(id);
        return chatRoom.orElse(null); // Retourne la chat room si elle existe
    }

    // Supprimer une discussion par son ID
    @Override
    public void deleteChatRoom(String id) {
        conversationRepository.deleteById(id);
    }

    @Override
    public List<MessageEntity> getMessagesByChatRoomId(String chatRoomId) {
        Optional<Conversation> chatRoom = conversationRepository.findById(chatRoomId);
        return chatRoom.map(Conversation::getMessages).orElse(Collections.emptyList());    }

}
