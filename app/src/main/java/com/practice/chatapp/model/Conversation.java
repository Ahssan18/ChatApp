package com.practice.chatapp.model;

public class Conversation {
    String lastMessage,timeStamp,senderId,senderName;

    public Conversation(String lastMessage, String timeStamp, String senderId, String senderName) {
        this.lastMessage = lastMessage;
        this.timeStamp = timeStamp;
        this.senderId = senderId;
        this.senderName = senderName;
    }

    public Conversation() {
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "lastMessage='" + lastMessage + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", senderId='" + senderId + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}
