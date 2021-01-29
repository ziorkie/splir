package com.ziora.splir.payload;

import com.ziora.splir.model.PaymentDetail;

public class UserPaymentDetailsResponse {
    UserResponse userResponse;
    PaymentDetail paymentDetail;

    public UserPaymentDetailsResponse(UserResponse userResponse, PaymentDetail paymentDetail) {
        this.userResponse = userResponse;
        this.paymentDetail = paymentDetail;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public PaymentDetail getPaymentDetail() {
        return paymentDetail;
    }

    public void setPaymentDetail(PaymentDetail paymentDetail) {
        this.paymentDetail = paymentDetail;
    }
}
