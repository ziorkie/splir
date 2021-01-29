package com.ziora.splir.repository;

import com.ziora.splir.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByReceiverId(Long id);
    List<Notification> findAllBySenderId(Long id);
}
