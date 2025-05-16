package Pfe_Education.mongo.service.Conversation;

import Pfe_Education.mongo.Entities.Conversation;
import Pfe_Education.mongo.Entities.MessageEntity;
import Pfe_Education.mongo.Entities.UserEntity;
import Pfe_Education.mongo.repositories.ConversationRepository;
import Pfe_Education.mongo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private UserRepo userRepository;



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

    @Override
    public void deleteChatRoom(String id) {
        conversationRepository.deleteById(id);
    }

    // methode pour supprimer tous les messages d'une conversation specifique


    @Override
    public List<MessageEntity> getMessagesByChatRoomId(String chatRoomId) {
        Optional<Conversation> chatRoom = conversationRepository.findById(chatRoomId);
        return chatRoom.map(Conversation::getMessages).orElse(Collections.emptyList());    }



    @Override
    public Conversation getChatRoomByMembersOrCreate(String senderFirebaseUid, String receiverFirebaseUid) {
        UserEntity sender = userRepository.findByFirebaseUid(senderFirebaseUid)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
        UserEntity receiver = userRepository.findByFirebaseUid(receiverFirebaseUid)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

        List<String> membersUids = List.of(senderFirebaseUid, receiverFirebaseUid);

        Optional<Conversation> existingRoom = conversationRepository.findByUserIdsExactly(membersUids, 2);

        if (existingRoom.isPresent()) {
            System.out.println("Conversation already exists");
            return existingRoom.get();
        }

        System.out.println("Creating new conversation...");
        Conversation newRoom = new Conversation();
        newRoom.setSenderId(senderFirebaseUid);
        newRoom.setReceiverId(receiverFirebaseUid);
        newRoom.setNom( receiver.getFirstname()+" " +receiver.getLastname());
        newRoom.setMembers(List.of(sender, receiver));
        newRoom.setUserIds(membersUids);
        newRoom.setMessages(new ArrayList<>());

        return conversationRepository.save(newRoom);
    }

    @Override
    public List<Conversation> getChatRoomsByUserId(String userId) {
        return conversationRepository.findByUserIdsContaining(userId);
    }
    // methode add message to conversation deja ouverte
    @Override
    public Conversation addMessageToConversation(String conversationId, MessageEntity message) {
        return conversationRepository.findById(conversationId)
                .map(conversation -> {
                    message.setDate(LocalDateTime.now());
                    conversation.getMessages().add(message);
                    return conversationRepository.save(conversation);
                })
                .orElseThrow(() -> new RuntimeException("Conversation not found with id: " + conversationId));
    }




}
