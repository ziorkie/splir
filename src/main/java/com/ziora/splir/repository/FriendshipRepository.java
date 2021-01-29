package com.ziora.splir.repository;

import com.ziora.splir.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    @Query("select f from Friendship f where f.friendshipIdentity.friendAId = :currentUserId or f.friendshipIdentity.friendBId = :currentUserId")
    List<Friendship> findFriendshipsById(@Param("currentUserId")Long currentUserId);

}
