package com.ziora.splir.repository;

import com.ziora.splir.model.Invitation;
import com.ziora.splir.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    @Query("SELECT i.invitationIdentity.invitedId FROM Invitation i where i.invitationIdentity.invitationId = :invitedId" )
    List<Long> findAllInvitedById(@Param("invitedId")Long invitedId);

    @Query("SELECT i.invitationIdentity.invitationId FROM Invitation i where i.invitationIdentity.invitedId = :invitationId" )
    List<Long> findAllInvitationForId(@Param("invitationId")Long invitationId);

    @Query("SELECT i from Invitation i WHERE i.invitationIdentity.invitationId = :invitationId and i.invitationIdentity.invitedId= :invitedId")
    Optional<Invitation> findByInvitationIdAndInvitatedId(@Param("invitationId")Long invitationId, @Param("invitedId")Long invitedId);
}
