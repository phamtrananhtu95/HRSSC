package com.hrssc.repository;

import com.hrssc.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    List<Notification> getByUserId(int userId);

}
