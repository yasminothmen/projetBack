package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.Entities.Conversation;
import Pfe_Education.mongo.Entities.MessageEntity;
import Pfe_Education.mongo.service.Conversation.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversation")
@CrossOrigin(origins = "*")

public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @PostMapping
    public ResponseEntity<Conversation> createChatRoom(@RequestBody Conversation chatRoom) {
        Conversation createdChatRoom = conversationService.createChatRoom(chatRoom);
        return new ResponseEntity<>(createdChatRoom, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Conversation>> getAllChatRooms() {
        List<Conversation> chatRooms = conversationService.getAllChatRooms();
        return new ResponseEntity<>(chatRooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conversation> getChatRoomById(@PathVariable String id) {
        Conversation chatRoom = conversationService.getChatRoomById(id);
        if (chatRoom != null) {
            return new ResponseEntity<>(chatRoom, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable String id) {
        conversationService.deleteChatRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{chatRoomId}/messages")
    public ResponseEntity<List<MessageEntity>> getMessages(@PathVariable String chatRoomId) {
        return ResponseEntity.ok(conversationService.getMessagesByChatRoomId(chatRoomId));
    }
    @GetMapping("/between/{senderId}/{receiverId}")
    public ResponseEntity<Conversation> getOrCreateChatRoom(
            @PathVariable String senderId,
            @PathVariable String receiverId) {
        Conversation chatRoom = conversationService.getChatRoomByMembersOrCreate(senderId, receiverId);
        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping("/user/{userId}")
    public List<Conversation> getChatRoomsByUserId(@PathVariable String userId) {
        return conversationService.getChatRoomsByUserId(userId);
    }

    @PutMapping("/{conversationId}/messages")
    public ResponseEntity<Conversation> addMessage(
            @PathVariable String conversationId,
            @RequestBody MessageEntity message) {
        return ResponseEntity.ok(conversationService.addMessageToConversation(conversationId, message));
    }

}
