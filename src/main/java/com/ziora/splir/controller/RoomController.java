package com.ziora.splir.controller;

import com.fasterxml.jackson.databind.node.TextNode;
import com.ziora.splir.payload.AddUserToRoomRequest;
import com.ziora.splir.payload.GroupExpenseRequest;
import com.ziora.splir.security.CurrentUser;
import com.ziora.splir.security.UserPrincipal;
import com.ziora.splir.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@CurrentUser UserPrincipal userPrincipal, @RequestBody String name) {
        roomService.createRoom(name, userPrincipal.getId());
        return new ResponseEntity("Utworzono wydarzenie!", HttpStatus.OK);
    }

    @PostMapping("/adduser")
    public ResponseEntity<String> addEventUser(@CurrentUser UserPrincipal userPrincipal, @RequestBody AddUserToRoomRequest addUserToRoomRequest) {
        roomService.addRoomUser(userPrincipal.getId(), addUserToRoomRequest.getUserId(), addUserToRoomRequest.getRoomId());
        return new ResponseEntity("Dodano użytkownika!", HttpStatus.OK);
    }

    @PostMapping("/addexpense")
    public ResponseEntity<String> addExpense(@CurrentUser UserPrincipal userPrincipal, @RequestBody GroupExpenseRequest groupExpenseRequest) {
        roomService.addExpense(userPrincipal.getId(), groupExpenseRequest);
        return new ResponseEntity("Dodano wydatekroom!", HttpStatus.OK);
    }
    

    @PostMapping("/close")
    public ResponseEntity<String> closeRoom(@CurrentUser UserPrincipal userPrincipal, @RequestBody AddUserToRoomRequest addUserToRoomRequest) {
        roomService.closeRoom(userPrincipal.getId(), addUserToRoomRequest.getRoomId());
        return new ResponseEntity("zamknieto pokoj!", HttpStatus.OK);
    }
}
