package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.entities.ChatRoomEntity;
import Pfe_Education.mongo.service.chat.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatrooms") // Point d'entrée pour les chat rooms
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<ChatRoomEntity> createChatRoom(@RequestBody ChatRoomEntity chatRoom) {
        ChatRoomEntity createdChatRoom = chatRoomService.createChatRoom(chatRoom);
        return new ResponseEntity<>(createdChatRoom, HttpStatus.CREATED); // Crée une nouvelle chat room
    }

    @GetMapping
    public ResponseEntity<List<ChatRoomEntity>> getAllChatRooms() {
        List<ChatRoomEntity> chatRooms = chatRoomService.getAllChatRooms();
        return new ResponseEntity<>(chatRooms, HttpStatus.OK); // Retourne toutes les chat rooms
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatRoomEntity> getChatRoomById(@PathVariable String id) {
        ChatRoomEntity chatRoom = chatRoomService.getChatRoomById(id);
        if (chatRoom != null) {
            return new ResponseEntity<>(chatRoom, HttpStatus.OK); // Retourne une chat room par son ID
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si non trouvé, retourne 404
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatRoomEntity> updateChatRoom(@PathVariable String id, @RequestBody ChatRoomEntity chatRoom) {
        ChatRoomEntity updatedChatRoom = chatRoomService.updateChatRoom(id, chatRoom);
        if (updatedChatRoom != null) {
            return new ResponseEntity<>(updatedChatRoom, HttpStatus.OK); // Met à jour la chat room
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si la chat room n'existe pas
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable String id) {
        chatRoomService.deleteChatRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Supprime la chat room et retourne 204
    }
}
