package com.hrssc.rest;

import com.hrssc.domain.chat.ChatMessage;
import com.hrssc.domain.chat.NotificationMessage;
import com.hrssc.listener.WebSocketListener;
import com.hrssc.service.ChatService;
import com.hrssc.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import static java.lang.String.format;

@Controller
public class NotificationController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private NotificationService notificationService;


    @MessageMapping("/notification/{roomId}/sendNotification")
    public void sendMessage(@DestinationVariable String roomId, @Payload NotificationMessage notificationData) {
        notificationService.saveNoti(notificationData);
        messagingTemplate.convertAndSend(format("/channel/%s", roomId), notificationData);
    }

    @MessageMapping("/notification/{roomId}/addUser")
    public void addUser(@DestinationVariable String roomId, @Payload NotificationMessage chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {
        String currentRoomId = (String) headerAccessor.getSessionAttributes().put("room_id", roomId);
        if (currentRoomId != null) {
            NotificationMessage leaveMessage = new NotificationMessage();
            leaveMessage.setType(NotificationMessage.MessageType.LEAVE);
            leaveMessage.setSender(chatMessage.getSender());
            messagingTemplate.convertAndSend(format("/channel/%s", currentRoomId), leaveMessage);
        }
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        messagingTemplate.convertAndSend(format("/channel/%s", roomId), chatMessage);
    }
}
