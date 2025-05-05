package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.Entities.MessageEntity;
import Pfe_Education.mongo.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")

public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageEntity> addMessage(@RequestBody MessageEntity message) {
        MessageEntity createdMessage = messageService.addMessage(message);
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }

    // Endpoint pour obtenir tous les messages
    @GetMapping
    public ResponseEntity<List<MessageEntity>> getAllMessages() {
        List<MessageEntity> messages = messageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageEntity> getMessageById(@PathVariable String id) {
        Optional<MessageEntity> message = messageService.getAllMessages()
                .stream()
                .filter(msg -> msg.getId().equals(id))
                .findFirst();
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable String id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();  // Retourner un code de succès 204 (pas de contenu)
    }


    @GetMapping("/chatroom/{chatroomId}")
    public List<MessageEntity> getChatRoomHistory(@PathVariable String chatroomId) {
        return messageService.getChatRoomHistory(chatroomId);
    }
    @GetMapping("/direct/{user1Id}/{user2Id}")
    public List<MessageEntity> getDirectMessagesHistory(
            @PathVariable String user1Id,
            @PathVariable String user2Id
    ) {
        return messageService.getDirectMessagesHistory(user1Id, user2Id);
    }
}