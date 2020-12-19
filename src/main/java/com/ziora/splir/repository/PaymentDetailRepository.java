package com.ziora.splir.repository;

import com.ziora.splir.model.PaymentDetail;
import com.ziora.splir.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {

}