package com.ziora.splir.service;

import com.ziora.splir.exception.UserNotFoundException;
import com.ziora.splir.model.Friendship;
import com.ziora.splir.model.FriendshipIdentity;
import com.ziora.splir.model.User;
import com.ziora.splir.payload.FriendshipResponse;
import com.ziora.splir.repository.FriendshipRepository;
import com.ziora.splir.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;


    public List<FriendshipResponse> getFriendships(Long currentUserId) throws UserNotFoundException{
        List<Friendship> friendsList = friendshipRepository.findFriendshipsById(currentUserId);
        List<FriendshipResponse> friendshipResponseList = new ArrayList<>();
        for(Friendship e:friendsList){
            FriendshipResponse friendshipResponse = new FriendshipResponse();
            if(e.getFriendshipIdentity().getFriendAId()==currentUserId){
                User user = userRepository.findById(e.getFriendshipIdentity().getFriendBId()).orElseThrow(()-> new UserNotFoundException("user with ID "+e+" not found"));
                friendshipResponse.setUserId(e.getFriendshipIdentity().getFriendBId());
                friendshipResponse.setUsername(user.getUsername());
                friendshipResponseList.add(friendshipResponse);
            }
            else{
                User user = userRepository.findById(e.getFriendshipIdentity().getFriendAId()).orElseThrow(()-> new UserNotFoundException("user with ID "+e+" not found"));
                friendshipResponse.setUserId(e.getFriendshipIdentity().getFriendAId());
                friendshipResponse.setUsername(user.getUsername());
                friendshipResponseList.add(friendshipResponse);
            }

        }
        return friendshipResponseList;
    }

    public Boolean isFriendship(Long currentUserId, Long userId){
        List<Friendship> friendsList = friendshipRepository.findFriendshipsById(currentUserId);
        for(Friendship e:friendsList){
            if(e.getFriendshipIdentity().getFriendAId()==currentUserId && e.getFriendshipIdentity().getFriendBId()==userId || e.getFriendshipIdentity().getFriendAId()==userId && e.getFriendshipIdentity().getFriendBId()==currentUserId ) {
                return true;
            }
        }
        return false;
    }

    public void deleteFriend(Long userId, Long friendId){

        FriendshipIdentity friendshipIdentity = new FriendshipIdentity(friendId, userId);
        Friendship friendship = new Friendship(friendshipIdentity);

        friendshipRepository.delete(friendship);

    }
}
