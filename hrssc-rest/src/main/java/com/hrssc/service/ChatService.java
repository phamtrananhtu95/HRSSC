package com.hrssc.service;

import com.hrssc.domain.chat.ChatMessage;
import com.hrssc.entities.ChatLog;

import java.util.List;

public interface ChatService {
    void saveLog(ChatMessage chatMessage);
    List<ChatLog> getMessageListByContractId(int contractId);
}
