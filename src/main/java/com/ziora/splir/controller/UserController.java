package com.ziora.splir.controller;

import com.ziora.splir.model.PaymentDetail;
import com.ziora.splir.payload.UserIdRequest;
import com.ziora.splir.payload.UserResponse;
import com.ziora.splir.repository.UserRepository;
import com.ziora.splir.security.CurrentUser;
import com.ziora.splir.security.UserPrincipal;
import com.ziora.splir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@CurrentUser UserPrincipal currentUser){
        UserResponse userResponse= userService.getCurrentUser(currentUser);
        return new ResponseEntity(userResponse, HttpStatus.OK);
    }

    @PostMapping("/userdetails")
    public ResponseEntity<UserResponse> getUserDetails(@RequestBody UserIdRequest userIdRequest){
        UserResponse userResponse= userService.getUserDetails(userIdRequest.getUserId());
        return new ResponseEntity(userResponse, HttpStatus.OK);
    }

    @PostMapping("/paymentdetails")
    public ResponseEntity<PaymentDetail> getPaymentDetails(@RequestBody UserIdRequest userId){
        PaymentDetail paymentDetail= userService.getPaymentDetails(userId.getUserId());
        return new ResponseEntity(paymentDetail, HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:3000/user")
    @GetMapping("/getall")
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }


}
