package com.hrssc.service.impl;

import com.hrssc.domain.chat.NotificationMessage;
import com.hrssc.entities.Notification;
import com.hrssc.repository.NotificationRepository;
import com.hrssc.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    Comparator<Notification> dateComparator = new Comparator<Notification>() {
        @Override
        public int compare(Notification o1, Notification o2) {
            if(o2.getDate() > o1.getDate()){
                return 1;
            }
            if(o2.getDate() < o1.getDate()){
                return -1;
            }
            return 0;
        }
    };
    public void saveNoti(NotificationMessage notificationData){
        Notification notification = new Notification();
        notification.setHumanResourceId(notificationData.getResourceId());
        notification.setProjectId(notificationData.getProjectId());
        notification.setContent(notificationData.getContent());
        notification.setType(notificationData.getNotiType());
        notification.setUserId(notificationData.getUserId());
        notification.setRead(false);
        notification.setDate(System.currentTimeMillis());
        notificationRepository.save(notification);

    }

    public List<Notification> getNotiLogList(int userId){
        List<Notification> notiList =  notificationRepository.getByUserId(userId);
        notiList.sort(dateComparator);
        return notiList;
    }
}
