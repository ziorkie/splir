package com.ziora.splir.repository;

import com.ziora.splir.model.Friendship;
import com.ziora.splir.model.PaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}
