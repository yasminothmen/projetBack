package Pfe_Education.mongo.service.chatRomm;

import Pfe_Education.mongo.Entities.ChatRoomEntity;
import java.util.List;

public interface ChatRoomService {
    ChatRoomEntity createChatRoom(ChatRoomEntity chatRoom);
    List<ChatRoomEntity> getAllChatRooms();
    ChatRoomEntity getChatRoomById(String id);
    void deleteChatRoom(String id);
    ChatRoomEntity updateChatRoom(String id, ChatRoomEntity chatRoom);}
