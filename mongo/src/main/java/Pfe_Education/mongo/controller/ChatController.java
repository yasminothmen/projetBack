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
        System.out.println("📩 [BACKEND] Message reçu - " +
                "Expéditeur: " + chatMessage.getSender() + " | " +
                "Contenu: '" + chatMessage.getContent() + "'");

        // Créer le message
        MessageEntity message = new MessageText();
        message.setChatroomId(chatMessage.getChatRoomId());
        message.setDate(LocalDateTime.now());
        message.setSenderId(chatMessage.getSender());
        ((MessageText) message).setText(chatMessage.getContent());

        // Sauvegarder dans la collection MessageEntity
        message = messageService.addMessage(message);

        // ✅ Ajouter aussi le message dans la conversation correspondante
        try {
            conversationService.addMessageToConversation(chatMessage.getChatRoomId(), message);
         System.out.println("✅ Message ajouté à la conversation.");
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de l'ajout du message à la conversation : ");
            e.printStackTrace();
        }

        // Récupérer la chatroom pour envoyer le message aux membres
        Conversation chatRoom = conversationService.getChatRoomById(chatMessage.getChatRoomId());

        if (chatRoom != null) {
            for (UserEntity user : chatRoom.getMembers()) {
                messagingTemplate.convertAndSend("/topic/messages/%s".formatted(user.getId()), message);

                String notification = "Nouveau message de " + chatMessage.getSender() + ": " + chatMessage.getContent();
                messagingTemplate.convertAndSend("/topic/notifications/%s".formatted(user.getId()), notification);

                System.out.println("📣 Notification envoyée à l'utilisateur " + user.getId() + ": " + notification);
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