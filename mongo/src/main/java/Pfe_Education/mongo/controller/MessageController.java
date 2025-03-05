package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.entities.MessageEntity;
import Pfe_Education.mongo.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")  // Définir le chemin de base pour ce contrôleur
public class MessageController {

    @Autowired
    private MessageService messageService;

    // Endpoint pour ajouter un message
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

    // Endpoint pour obtenir un message par ID
    @GetMapping("/{id}")
    public ResponseEntity<MessageEntity> getMessageById(@PathVariable String id) {
        Optional<MessageEntity> message = messageService.getAllMessages()
                .stream()
                .filter(msg -> msg.getId().equals(id))
                .findFirst();
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint pour supprimer un message
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable String id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();  // Retourner un code de succès 204 (pas de contenu)
    }
}
