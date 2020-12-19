package com.ziora.splir.service;

import com.ziora.splir.exception.InvitationNotFoundException;
import com.ziora.splir.exception.UserNotFoundException;
import com.ziora.splir.model.*;
import com.ziora.splir.payload.InvitedUserResponse;
import com.ziora.splir.repository.FriendshipRepository;
import com.ziora.splir.repository.InvitationRepository;
import com.ziora.splir.repository.UserRepository;
import com.ziora.splir.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    FriendshipRepository friendshipRepository;

    public void sendInvite(Long inviterId, Long invitedId){
        InvitationIdentity invitationIdentity= new InvitationIdentity(inviterId, invitedId);
        Invitation invitation = new Invitation(invitationIdentity);
        invitationRepository.save(invitation);
    }

    public List<InvitedUserResponse> checkSentInvites(Long currentUserId) throws UserNotFoundException {
        List<Long> invitedList = invitationRepository.findAllInvitedById(currentUserId);
        List<InvitedUserResponse> invitedUserResponseList = new ArrayList<>();
        for(Long e:invitedList){
            InvitedUserResponse invitedUserResponse = new InvitedUserResponse();
            User user = userRepository.findById(e).orElseThrow(()-> new UserNotFoundException("user with ID "+e+" not found"));
            invitedUserResponse.setUserId(e);
            invitedUserResponse.setUsername(user.getUsername());
            invitedUserResponseList.add(invitedUserResponse);
        }
        return invitedUserResponseList;
    }

    public List<InvitedUserResponse> checkReceivedInvites(Long currentUserId) throws UserNotFoundException {
        List<Long> invitedList = invitationRepository.findAllInvitationForId(currentUserId);
        List<InvitedUserResponse> invitedUserResponseList = new ArrayList<>();
        for(Long e:invitedList){
            InvitedUserResponse invitedUserResponse = new InvitedUserResponse();
            User user = userRepository.findById(e).orElseThrow(()-> new UserNotFoundException("user with ID "+e+" not found"));
            invitedUserResponse.setUserId(e);
            invitedUserResponse.setUsername(user.getUsername());
            invitedUserResponseList.add(invitedUserResponse);
        }
        return invitedUserResponseList;
    }

    public void deleteInvite(Long userId, Long invitedId){

        InvitationIdentity invitationIdentity = new InvitationIdentity(userId, invitedId);
        Invitation invitation = new Invitation(invitationIdentity);
        invitationRepository.delete(invitation);
    }

    @Transactional
    public void acceptInvite(Long userId, Long invitationId){
       Invitation invitation =  invitationRepository.findByInvitationIdAndInvitatedId(invitationId, userId).orElseThrow(
               ()->new InvitationNotFoundException("Invitation not found")
       );

            FriendshipIdentity friendshipIdentity = new FriendshipIdentity(invitationId, userId);
            Friendship friendship = new Friendship(friendshipIdentity);

            friendshipRepository.save(friendship);
            invitationRepository.delete(invitation);

    }
}
