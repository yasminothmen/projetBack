package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.Entities.ChatMessage;
import Pfe_Education.mongo.Entities.Conversation;
import Pfe_Education.mongo.Entities.MessageEntity;
import Pfe_Education.mongo.Entities.MessageText;
import Pfe_Education.mongo.Entities.UserEntity;
import Pfe_Education.mongo.service.Conversation.ConversationService;
import Pfe_Education.mongo.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

@Controller
@CrossOrigin(origins = "*")

public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ConversationService conversationService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(ChatMessage chatMessage) {
        // 1. Log immédiat pour confirmer la réception
        System.out.println("📩 [BACKEND] Message reçu - " +
                "Expéditeur: " + chatMessage.getSender() + " | " +
                "Contenu: '" + chatMessage.getContent() + "' | " +
                "ChatRoom ID: " + chatMessage.getChatRoomId()
        );
        // Créer et enregistrer le message en base de données
        MessageEntity message= new MessageText();
        message.setChatroomId(chatMessage.getChatRoomId());
        message.setDate(LocalDateTime.now());
        message.setSenderId(chatMessage.getSender());
        ((MessageText) message).setText(chatMessage.getContent());
        message = messageService.addMessage(message);

        // Récupérer le chatroom et envoyer des notifications aux membres
        Conversation chatRoom = conversationService.getChatRoomById(chatMessage.getChatRoomId());
        if (chatRoom != null) {
            for (UserEntity user : chatRoom.getMembers()) {
                // Envoyer le message à l'utilisateur via WebSocket
                messagingTemplate.convertAndSend("/topic/messages/%s".formatted(user.getId()), message);

                // Envoyer une notification en temps réel à l'utilisateur
                String notificationMessage = "Nouveau message de " + chatMessage.getSender() + ": " + chatMessage.getContent();
                messagingTemplate.convertAndSend("/topic/notifications/%s".formatted(user.getId()), notificationMessage);

                // Log pour débogage
                System.out.println("📣 Notification envoyée à l'utilisateur " + user.getId() + ": " + notificationMessage);
            }
        }
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Ajouter l'utilisateur à la session WebSocket
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}