package com.ziora.splir.service;

import com.ziora.splir.exception.UserNotFoundException;
import com.ziora.splir.model.Notification;
import com.ziora.splir.model.Room;
import com.ziora.splir.model.GroupExpense;
import com.ziora.splir.model.User;
import com.ziora.splir.payload.CloseRoomHelper;
import com.ziora.splir.payload.GroupExpenseRequest;
import com.ziora.splir.repository.NotificationRepository;
import com.ziora.splir.repository.RoomRepository;
import com.ziora.splir.repository.GroupExpenseRepository;
import com.ziora.splir.repository.UserRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.*;

import static java.lang.Math.abs;

@Service
public class RoomService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GroupExpenseRepository groupExpenseRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public void createRoom(String name, Long administratorId){
        Room room = new Room();
        Set<User> users = new HashSet<>();
        room.setAdministratorId(administratorId);
        room.setName(name);
        users.add(userRepository.findById(administratorId).orElseThrow(()->new UserNotFoundException("CRITICAL")));
        room.setUsers(users);
        roomRepository.save(room);
    }

    public void addRoomUser(Long currentUserId, Long userId, Long roomId){
        Room room = roomRepository.findById(roomId).orElseThrow(()->new UserNotFoundException("change_me"));
        if(!room.getAdministratorId().equals(currentUserId)){
            throw new UserNotFoundException("z czym do ludzi");
        }
        room.getUsers().add(userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("user not found")));
        roomRepository.save(room);
    }

    public void removeUser(Long currentUserId, Long userId, Long roomId){
        Room room = roomRepository.findById(roomId).orElseThrow(()->new UserNotFoundException("change_me"));
        if(!room.getAdministratorId().equals(currentUserId)){
            throw new UserNotFoundException("z czym do ludzi");
        }
        room.getUsers().remove(userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("user not found")));
        roomRepository.save(room);
    }

    public void addExpense(Long userId, GroupExpenseRequest groupExpenseRequest){
        Room room = roomRepository.findById(groupExpenseRequest.getRoomId()).orElseThrow(()->new UserNotFoundException("change_me"));
        if (!room.getUsers().contains(userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("user not found"))))
            throw new UserNotFoundException("change me");
        GroupExpense groupExpense = new GroupExpense();

        groupExpense.setExpenseName(groupExpenseRequest.getName());
        groupExpense.setExpenseValue(groupExpenseRequest.getValue());
        groupExpense.setUserId(userId);
        groupExpense.setRoomId(groupExpenseRequest.getRoomId());
        room.setTotalExpense(room.getTotalExpense()+groupExpenseRequest.getValue());
        groupExpenseRepository.save(groupExpense);
        roomRepository.save(room);
    }


      public void closeRoom(Long userId, Long roomId){
        Room room = roomRepository.findById(roomId).orElseThrow(()->new UserNotFoundException("change_me"));
          if(!userId.equals(room.getAdministratorId()))
            throw new UserNotFoundException("omg");
          Double averageValue = room.getTotalExpense()/room.getUsers().size();
          //TODO:optimize this, these sets are not necessary
          Set<CloseRoomHelper> totalExpensePerUser = new HashSet<>();
          Set<CloseRoomHelper> differenceFromAverage = new HashSet<>();
          Stack<CloseRoomHelper> creditors = new Stack<CloseRoomHelper>();
          Stack<CloseRoomHelper> debtors = new Stack<CloseRoomHelper>();
        for(User u : room.getUsers()){
            CloseRoomHelper closeRoomHelper = new CloseRoomHelper(u.getId(), groupExpenseRepository.getTotalExpenseByUserAndRoom(roomId, u.getId()).orElseGet(()->0.0));
            CloseRoomHelper closeRoomHelper2 = new CloseRoomHelper(closeRoomHelper.getUserId(), closeRoomHelper.getValue());
            totalExpensePerUser.add(closeRoomHelper);
            closeRoomHelper2.setValue(averageValue-closeRoomHelper.getValue());
            differenceFromAverage.add(closeRoomHelper2);

            if(averageValue+closeRoomHelper2.getValue()>=0){
                debtors.push(closeRoomHelper2);
            }
            else{
                creditors.push(closeRoomHelper2);
            }

        }
        while(!creditors.empty()){
            Notification notification = new Notification();
            if(debtors.peek().getValue()>=abs(creditors.peek().getValue())){
                debtors.peek().setValue(debtors.peek().getValue()+creditors.peek().getValue());
                notification.setSenderId(creditors.peek().getUserId());
                notification.setReceiverId(debtors.peek().getUserId());
                notification.setMessage("you owe " + abs(creditors.peek().getValue()) + " to user " + creditors.peek().getUserId());
                notificationRepository.save(notification);
                creditors.pop();
            }
            else{
                creditors.peek().setValue((creditors.peek().getValue()+debtors.peek().getValue()));
                notification.setSenderId(creditors.peek().getUserId());
                notification.setReceiverId(debtors.peek().getUserId());
                notification.setMessage("you owe " + debtors.peek().getValue() + "to" + creditors.peek().getUserId());
                notificationRepository.save(notification);
                debtors.pop();
            }
        }
//        roomRepository.delete(room);
      }



}
