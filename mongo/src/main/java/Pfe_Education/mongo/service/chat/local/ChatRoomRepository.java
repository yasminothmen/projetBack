package Pfe_Education.mongo.service.chat.local;

import Pfe_Education.mongo.entities.ChatRoomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoomEntity, String> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées ici, si nécessaire
    ChatRoomEntity findByNom(String nom);
}
