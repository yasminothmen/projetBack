package Pfe_Education.mongo.repositories;

import Pfe_Education.mongo.Entities.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends MongoRepository<Conversation, String> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées ici, si nécessaire
    Conversation findByNom(String nom);
}
