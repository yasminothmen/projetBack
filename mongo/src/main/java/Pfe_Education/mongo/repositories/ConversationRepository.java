package Pfe_Education.mongo.repositories;

import Pfe_Education.mongo.Entities.Conversation;
import Pfe_Education.mongo.Entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends MongoRepository<Conversation, String> {
    @Query("{ 'userIds': { $all: ?0 }, $expr: { $eq: [ { $size: '$userIds' }, ?1 ] } }")
    Optional<Conversation> findByUserIdsExactly(List<String> userIds, int size);


    // Alternative: Recherche une salle qui contient exactement 2 membres donnés (par leurs IDs)
    @Query("{ 'members._id': { $all: [?0, ?1] }, $where: 'this.members.length == 2' }")
    Optional<Conversation> findChatRoomWithExactTwoMembers(String userId1, String userId2);

    List<Conversation> findByUserIdsContaining(String userId);


}
