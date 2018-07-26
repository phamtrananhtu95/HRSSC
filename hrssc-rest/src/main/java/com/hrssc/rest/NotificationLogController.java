package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.chat.NotificationMessage;
import com.hrssc.domain.jacksonview.NotificationView;
import com.hrssc.entities.Notification;
import com.hrssc.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notification-log")
public class NotificationLogController {

    @Autowired
    NotificationService notificationService;



    @PostMapping(value = "/save")
    public void saveLog(@RequestBody NotificationMessage notificationData){
        notificationService.saveNoti(notificationData);
    }

    @JsonView(NotificationView.class)
    @GetMapping(value = "get-noti/{userId}")
    public List<Notification> getNotiLog(@PathVariable(value = "userId")int userId){
       return notificationService.getNotiLogList(userId);
    }
}
