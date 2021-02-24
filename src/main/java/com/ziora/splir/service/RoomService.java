package com.ziora.splir.service;

import com.ziora.splir.exception.FriendshipNotFoundException;
import com.ziora.splir.exception.RoomNotFoundException;
import com.ziora.splir.exception.UserNotFoundException;
import com.ziora.splir.model.*;
import com.ziora.splir.payload.CloseRoomHelper;
import com.ziora.splir.payload.GroupExpenseRequest;
import com.ziora.splir.payload.UserResponse;
import com.ziora.splir.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

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

    @Autowired
    private UserService userService;

    @Autowired
    private SoloExpenseRepository soloExpenseRepository;

    @Autowired
    private FriendshipService friendshipService;

    public void createRoom(String name, Long administratorId){
        Room room = new Room();
        Set<User> users = new HashSet<>();
        room.setAdministratorId(administratorId);
        room.setName(name);
        users.add(userRepository.findById(administratorId).orElseThrow(()->new UserNotFoundException("CRITICAL ERROR - Current user doesn't exist!")));
        room.setUsers(users);
        roomRepository.save(room);
    }

    public List<Room> getRooms(Long currentUserId) throws RoomNotFoundException{
        List<Room> roomList = roomRepository.findAllByUsers_id(currentUserId);
        return roomList;
    }

    public List<UserResponse> getUsers(Long roomId) throws UserNotFoundException{
        List<User> userList = userRepository.findAllByRoom_id(roomId);
        List<UserResponse> userResponseList = userList.stream().map(user -> new UserResponse(user.getId(), user.getUsername(), user.getName())).collect(Collectors.toList());
        return userResponseList;
    }

    public List<GroupExpense> getRoomExpenses(Long roomId) throws RoomNotFoundException{
        List<GroupExpense> groupExpenseList = groupExpenseRepository.findByRoomId(roomId);
        return groupExpenseList;
    }

    public Double getExpensePerUser(Long roomId) throws RoomNotFoundException{
        Room room = roomRepository.findById(roomId).orElseThrow(()->new RoomNotFoundException("Couldn't find specified room!"));
        return room.getTotalExpense()/room.getUsers().size();
    }


    public void addRoomUser(Long currentUserId, Long userId, Long roomId){
        if(!friendshipService.isFriendship(currentUserId, userId)) {
            throw new FriendshipNotFoundException("You are not a friend with this user");
        }
            Room room = roomRepository.findById(roomId).orElseThrow(()->new RoomNotFoundException("Couldn't find specified room!"));
            if(!room.getAdministratorId().equals(currentUserId)){
            throw new UserNotFoundException("z czym do ludzi");
        }
        room.getUsers().add(userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("user not found")));
        roomRepository.save(room);

    }

    public void removeUser(Long currentUserId, Long userId, Long roomId){
        Room room = roomRepository.findById(roomId).orElseThrow(()->new RoomNotFoundException("Couldn't find specified room!"));
        if(!room.getAdministratorId().equals(currentUserId)){
            throw new UserNotFoundException("z czym do ludzi");
        }
        room.getUsers().remove(userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found!")));
        roomRepository.save(room);
    }

    public void addExpense(Long userId, GroupExpenseRequest groupExpenseRequest){
        Room room = roomRepository.findById(groupExpenseRequest.getRoomId()).orElseThrow(()->new RoomNotFoundException("Couldn't find specified room!"));
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
        Room room = roomRepository.findById(roomId).orElseThrow(()->new RoomNotFoundException("Couldn't find specified room!"));
          if(!userId.equals(room.getAdministratorId()))
            throw new UserNotFoundException("omg");
          String text = Double.toString(Math.abs(room.getTotalExpense()/room.getUsers().size()));
          int integerPlaces = text.indexOf('.');
          int decimalPlaces = text.length() - integerPlaces - 1;
          Double averageValue = Math.round(room.getTotalExpense()/room.getUsers().size()*100.0)/100.0;
          if(((room.getTotalExpense()/room.getUsers().size()%1)<0.5)&&decimalPlaces>2){
              averageValue=averageValue+0.01;
          }

          Stack<CloseRoomHelper> creditors = new Stack<CloseRoomHelper>();
          Stack<CloseRoomHelper> debtors = new Stack<CloseRoomHelper>();
        for(User u : room.getUsers()){
            CloseRoomHelper closeRoomHelper = new CloseRoomHelper(u.getId(), groupExpenseRepository.getTotalExpenseByUserAndRoom(roomId, u.getId()).orElseGet(()->0.0));
            CloseRoomHelper closeRoomHelper2 = new CloseRoomHelper(closeRoomHelper.getUserId(), closeRoomHelper.getValue());
            closeRoomHelper2.setValue(averageValue-closeRoomHelper.getValue());
            SoloExpense soloExpense = new SoloExpense(u.getId(), room.getName(), averageValue, LocalDate.now(), false);
            soloExpenseRepository.save(soloExpense);
            if(closeRoomHelper2.getValue()>=0){
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
                notification.setMessage("you owe " + Math.round(abs(creditors.peek().getValue())*100.0)/100.0 + " to user " + userService.getUserDetails(creditors.peek().getUserId()).getUsername());
                notificationRepository.save(notification);
                creditors.pop();
            }
            else{
                creditors.peek().setValue((creditors.peek().getValue()+debtors.peek().getValue()));
                notification.setSenderId(creditors.peek().getUserId());
                notification.setReceiverId(debtors.peek().getUserId());
                notification.setMessage("you owe " + Math.round(abs(debtors.peek().getValue())*100.0)/100.0 + " to user " + userService.getUserDetails(creditors.peek().getUserId()).getUsername());
                notificationRepository.save(notification);
                debtors.pop();
            }
        }
        roomRepository.delete(room);
      }



}
