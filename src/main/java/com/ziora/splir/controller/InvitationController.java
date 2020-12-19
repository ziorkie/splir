package com.ziora.splir.controller;

import com.ziora.splir.exception.UserNotFoundException;
import com.ziora.splir.model.User;
import com.ziora.splir.payload.InvitedUserResponse;
import com.ziora.splir.payload.UserIdRequest;
import com.ziora.splir.repository.UserRepository;
import com.ziora.splir.security.CurrentUser;
import com.ziora.splir.security.UserPrincipal;
import com.ziora.splir.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invitations")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;


    @PostMapping("/send")
    public ResponseEntity<String> sendInvite(@CurrentUser UserPrincipal userPrincipal, @RequestBody UserIdRequest userId) {
        invitationService.sendInvite(userPrincipal.getId(), userId.getUserId());
        return new ResponseEntity("Wysłano zaproszenie", HttpStatus.OK);
    }

    @GetMapping("/mysentinvites")
    public ResponseEntity<List<InvitedUserResponse>> checkSentInvites(@CurrentUser UserPrincipal userPrincipal) {
        List<InvitedUserResponse> invitedUserResponseList = invitationService.checkSentInvites(userPrincipal.getId());
        return new ResponseEntity(invitedUserResponseList, HttpStatus.OK);
    }

    @GetMapping("/myreceivedinvites")
    public ResponseEntity<List<InvitedUserResponse>> checkReceivedInvites(@CurrentUser UserPrincipal userPrincipal) {
        List<InvitedUserResponse> invitedUserResponseList = invitationService.checkReceivedInvites(userPrincipal.getId());
        return new ResponseEntity(invitedUserResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteInvite(@CurrentUser UserPrincipal userPrincipal, @RequestBody UserIdRequest userId) {
        invitationService.deleteInvite(userPrincipal.getId(), userId.getUserId());
        return new ResponseEntity("Invite deleted!", HttpStatus.OK);
    }

    @PostMapping("/accept")
    public ResponseEntity<String> acceptInvite(@CurrentUser UserPrincipal userPrincipal, @RequestBody UserIdRequest userId) {
        invitationService.acceptInvite(userPrincipal.getId(), userId.getUserId());
        return new ResponseEntity("Invite accepted!", HttpStatus.OK);
    }
}
