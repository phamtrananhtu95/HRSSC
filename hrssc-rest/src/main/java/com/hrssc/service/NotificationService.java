package com.hrssc.service;

import com.hrssc.domain.chat.NotificationMessage;
import com.hrssc.entities.Notification;

import java.util.List;

public interface NotificationService {
    void saveNoti(NotificationMessage notificationData);
    List<Notification> getNotiLogList(int userId);
}
