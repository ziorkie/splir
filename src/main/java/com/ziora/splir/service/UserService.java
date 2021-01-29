package com.ziora.splir.service;

import com.ziora.splir.exception.UserNotFoundException;
import com.ziora.splir.model.PaymentDetail;
import com.ziora.splir.model.User;
import com.ziora.splir.payload.UserResponse;
import com.ziora.splir.repository.PaymentDetailRepository;
import com.ziora.splir.repository.UserRepository;
import com.ziora.splir.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentDetailRepository paymentDetailRepository;

    public UserResponse getCurrentUser(UserPrincipal userPrincipal){

        return new UserResponse(userPrincipal.getId(), userPrincipal.getUsername(), userPrincipal.getName());
    }

    public UserResponse getUserDetails(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("user not found"));
        return new UserResponse(user.getId(), user.getUsername(), user.getName());
    }

    public PaymentDetail getPaymentDetails(Long userId){
        return paymentDetailRepository.findByUserId(userId);
    }




    public List<UserResponse> getAllUsers(){
        return userRepository.getAll();
    }



}
