package com.ziora.splir.controller;


import com.ziora.splir.payload.NotificationIdRequest;
import com.ziora.splir.payload.NotificationResponse;
import com.ziora.splir.security.CurrentUser;
import com.ziora.splir.security.UserPrincipal;
import com.ziora.splir.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getnotifications")
    public ResponseEntity<List<NotificationResponse>> getNotifications(@CurrentUser UserPrincipal userPrincipal) {
        List<NotificationResponse> notificationResponses = notificationService.getNotifications(userPrincipal.getId());
        return new ResponseEntity(notificationResponses, HttpStatus.OK);
    }


    @GetMapping("/getsentnotifications")
    public ResponseEntity<List<NotificationResponse>> getDebtors(@CurrentUser UserPrincipal userPrincipal) {
        List<NotificationResponse> notificationResponses = notificationService.getDebtors(userPrincipal.getId());
        return new ResponseEntity(notificationResponses, HttpStatus.OK);
    }

    @PostMapping("/setseen")
    public ResponseEntity<String> setSeen(@CurrentUser UserPrincipal userPrincipal, @RequestBody NotificationIdRequest notificationId){
        notificationService.setSeen(userPrincipal.getId(), notificationId.getId());
        return new ResponseEntity("status set to seen", HttpStatus.OK);

    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeNotification(@CurrentUser UserPrincipal userPrincipal, @RequestBody NotificationIdRequest notificationId){
        notificationService.removeNotification(userPrincipal.getId(), notificationId.getId());
        return new ResponseEntity("notification removed", HttpStatus.OK);

    }

}
