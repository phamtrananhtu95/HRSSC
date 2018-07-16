package com.hrssc.service.impl;

import com.hrssc.domain.chat.ChatMessage;
import com.hrssc.entities.ChatLog;
import com.hrssc.repository.ChatLogRepository;
import com.hrssc.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatLogRepository chatLogRepository;


    @Override
    public void saveLog(ChatMessage chatMessage) {
        ChatLog chatLog = new ChatLog();
        chatLog.setMessageContent(chatMessage.getContent());
        chatLog.setContractId(chatMessage.getContractId());
        chatLog.setUserId(chatMessage.getUserId());

        long currentTimeMillis = System.currentTimeMillis();
        chatLog.setSentDate(currentTimeMillis);

        Date date = new Date(currentTimeMillis);
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        chatLog.setSentTime(timeFormatter.format(date));

        chatLogRepository.save(chatLog);
    }

    @Override
    public List<ChatLog> getMessageListByContractId(int contractId) {
        return chatLogRepository.findByContractId(contractId);
    }
}
