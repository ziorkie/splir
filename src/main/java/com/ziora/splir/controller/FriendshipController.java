package com.ziora.splir.controller;

import com.ziora.splir.payload.FriendshipResponse;
import com.ziora.splir.payload.UserIdRequest;
import com.ziora.splir.security.CurrentUser;
import com.ziora.splir.security.UserPrincipal;
import com.ziora.splir.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendship")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    @GetMapping("/myfriendships")
    public ResponseEntity<List<FriendshipResponse>> getFriendships(@CurrentUser UserPrincipal userPrincipal){
        List<FriendshipResponse> friendshipResponseList = friendshipService.getFriendships(userPrincipal.getId());
        return new ResponseEntity(friendshipResponseList, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteFriend(@CurrentUser UserPrincipal userPrincipal, @RequestBody UserIdRequest userId) {
        friendshipService.deleteFriend(userPrincipal.getId(), userId.getUserId());
        return new ResponseEntity("Friend deleted!", HttpStatus.OK);
    }

}
