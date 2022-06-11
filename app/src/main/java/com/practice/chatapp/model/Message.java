package com.practice.chatapp.model;

public class Message {
    String messageTitle, senderId, time;

    public Message(String messageTitle, String senderId, String time) {
        this.messageTitle = messageTitle;
        this.senderId = senderId;
        this.time = time;
    }

    public Message() {
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageTitle='" + messageTitle + '\'' +
                ", senderId='" + senderId + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
