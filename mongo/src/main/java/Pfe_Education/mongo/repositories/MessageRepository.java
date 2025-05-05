package Pfe_Education.mongo.repositories;

import Pfe_Education.mongo.Entities.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<MessageEntity, String> {
    List<MessageEntity> findByChatroomId(String chatroomId);
    // besh ne5ou lhistorique mtaa conversation m bin zouz
    @Query("{$or: [ {senderId: ?0, recipientId: ?1, chatroomId: null}, {senderId: ?1, recipientId: ?0, chatroomId: null} ] }")
    List<MessageEntity> findDirectMessages(String user1Id, String user2Id);

}
