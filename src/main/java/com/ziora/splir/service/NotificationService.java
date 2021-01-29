package com.ziora.splir.service;

import com.ziora.splir.exception.UserNotFoundException;
import com.ziora.splir.model.Notification;
import com.ziora.splir.payload.NotificationResponse;
import com.ziora.splir.repository.GroupExpenseRepository;
import com.ziora.splir.repository.NotificationRepository;
import com.ziora.splir.repository.RoomRepository;
import com.ziora.splir.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class NotificationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GroupExpenseRepository groupExpenseRepository;

    @Autowired
    private  UserService userService;



    @Autowired
    private NotificationRepository notificationRepository;

    public List<NotificationResponse> getNotifications(Long currentUserId) throws UserNotFoundException{
        List<NotificationResponse> notificationResponseList = notificationRepository.findAllByReceiverId(currentUserId).stream().map(
                notification -> new NotificationResponse(notification.getId(), notification.getMessage(), notification.getSenderId(), notification.getSeen(), userService.getUserDetails(notification.getSenderId()).getUsername(), userService.getUserDetails(notification.getReceiverId()).getUsername())).collect(Collectors.toList());;

        return notificationResponseList;
    }

    public List<NotificationResponse> getDebtors(Long currentUserId) throws UserNotFoundException{
        List<NotificationResponse> notificationResponseList = notificationRepository.findAllBySenderId(currentUserId).stream().map(
                notification -> new NotificationResponse(notification.getId(), notification.getMessage().replace("you owe","Value of debt: ").replace("to user","need to be returned to: ").concat(" by:"),
                        notification.getReceiverId(), notification.getSeen(), userService.getUserDetails(notification.getSenderId()).getUsername(),
                        userService.getUserDetails(notification.getReceiverId()).getUsername() )).collect(Collectors.toList());;

        return notificationResponseList;
    }


    public List<NotificationResponse> getCreditors(Long currentUserId) throws UserNotFoundException{
        List<NotificationResponse> notificationResponseList = notificationRepository.findAllBySenderId(currentUserId).stream().map(
                notification -> new NotificationResponse(notification.getId(), notification.getMessage().replace("you owe","Value of debt: ").replace("to user","debtor: "), notification.getSenderId(), notification.getSeen(),userService.getUserDetails(notification.getSenderId()).getUsername(), userService.getUserDetails(notification.getReceiverId()).getUsername())).collect(Collectors.toList());;

        return notificationResponseList;
    }

    public void setSeen(Long currentUserId, Long notificationId){
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        if(currentUserId==notification.getReceiverId()) {
            notification.setSeen(true);
            notificationRepository.save(notification);
        }
    }

    public void removeNotification(Long currentUserId, Long notificationId){
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        if(currentUserId==notification.getSenderId()) {
            notificationRepository.delete(notification);
        }
    }




}





