package Pfe_Education.mongo.service.chat.local;

import Pfe_Education.mongo.entities.ChatRoomEntity;
import Pfe_Education.mongo.service.chat.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultChatRoomService implements ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoomEntity createChatRoom(ChatRoomEntity chatRoom) {
        return chatRoomRepository.save(chatRoom); // Sauvegarde dans la base de données
    }

    @Override
    public List<ChatRoomEntity> getAllChatRooms() {
        return chatRoomRepository.findAll(); // Récupère toutes les chat rooms
    }

    @Override
    public ChatRoomEntity getChatRoomById(String id) {
        Optional<ChatRoomEntity> chatRoom = chatRoomRepository.findById(id);
        return chatRoom.orElse(null); // Retourne la chat room si elle existe
    }

    @Override
    public void deleteChatRoom(String id) {
        chatRoomRepository.deleteById(id); // Supprime la chat room par son ID
    }

    @Override
    public ChatRoomEntity updateChatRoom(String id, ChatRoomEntity chatRoom) {
        if (chatRoomRepository.existsById(id)) {
            chatRoom.setId(id);
            return chatRoomRepository.save(chatRoom); // Met à jour la chat room
        }
        return null;
    }
}
