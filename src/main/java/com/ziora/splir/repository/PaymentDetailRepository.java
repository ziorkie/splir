package com.ziora.splir.repository;

import com.ziora.splir.model.PaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {

    PaymentDetail findByUserId(Long userId);
}