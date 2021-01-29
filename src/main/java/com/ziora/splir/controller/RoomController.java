package com.ziora.splir.controller;

import com.ziora.splir.model.GroupExpense;
import com.ziora.splir.model.Room;
import com.ziora.splir.payload.*;
import com.ziora.splir.security.CurrentUser;
import com.ziora.splir.security.UserPrincipal;
import com.ziora.splir.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@CurrentUser UserPrincipal userPrincipal, @RequestBody StringBody name) {
        roomService.createRoom(name.getName(), userPrincipal.getId());
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
    public ResponseEntity<String> closeRoom(@CurrentUser UserPrincipal userPrincipal, @RequestBody RoomIdRequest roomId) {
        roomService.closeRoom(userPrincipal.getId(), roomId.getRoomId());
        return new ResponseEntity("zamknieto pokoj!", HttpStatus.OK);
    }

    @GetMapping("/getrooms")
    public ResponseEntity<List<RoomResponse>> getRooms(@CurrentUser UserPrincipal userPrincipal) {
        List<Room> roomResponses = roomService.getRooms(userPrincipal.getId());
        return new ResponseEntity(roomResponses, HttpStatus.OK);
    }


    @PostMapping("/getexpenseperuser")
    public ResponseEntity<Double> getExpensePerUser(@CurrentUser UserPrincipal userPrincipal, @RequestBody RoomIdRequest roomIdRequest) {
        Double expensePerUser = roomService.getExpensePerUser(roomIdRequest.getRoomId());
        return new ResponseEntity(expensePerUser, HttpStatus.OK);
    }





    @PostMapping("/getroomusers")
    public ResponseEntity<List<UserResponse>> getRoomUsers(@CurrentUser UserPrincipal userPrincipal, @RequestBody RoomIdRequest roomId) {
        List<UserResponse> userList = roomService.getUsers(roomId.getRoomId());
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @PostMapping("/roomexpenses")
    public ResponseEntity<List<GroupExpense>> getRoomExpenses(@CurrentUser UserPrincipal userPrincipal, @RequestBody RoomIdRequest roomId) {
        List<GroupExpense> groupExpenseList = roomService.getRoomExpenses(roomId.getRoomId());
        return new ResponseEntity(groupExpenseList, HttpStatus.OK);
    }

}
