package com.hrssc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "chat_log", schema = "hrssc")
public class ChatLog {
    private int id;
    private long sentDate;
    private String sentTime;
    private String messageContent;
    private int userId;
    private User userByUserId;
    private int contractId;
    private Contract contractByContractId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sent_date", nullable = false)
    public long getSentDate() {
        return sentDate;
    }

    public void setSentDate(long sentDate) {
        this.sentDate = sentDate;
    }

    @Basic
    @Column(name = "sent_time", nullable = false)
    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    @Basic
    @Column(name = "message_content", nullable = false, length = 500)
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatLog chatLog = (ChatLog) o;
        return id == chatLog.id &&
                sentDate == chatLog.sentDate &&
                sentTime.equals(chatLog.sentTime) &&
                userId == chatLog.userId &&
                Objects.equals(messageContent, chatLog.messageContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sentDate, sentTime, messageContent, userId);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @Basic
    @Column(name = "contract_id", nullable = false)
    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    @ManyToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public Contract getContractByContractId() {
        return contractByContractId;
    }

    public void setContractByContractId(Contract contractByContractId) {
        this.contractByContractId = contractByContractId;
    }
}
