package Pfe_Education.mongo.repositories;

import Pfe_Education.mongo.Entities.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<MessageEntity, String> {
    // Ici tu peux ajouter des méthodes personnalisées si nécessaire
    // Par exemple, pour trouver des messages par expéditeur ou par contenu.
}
