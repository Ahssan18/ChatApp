package com.practice.chatapp.model;

public class Conversation {
    String lastMessage;
    String timeStamp;
    String senderId;
    String receiverId;
    String senderName;
    String conversationId;

    public Conversation(String lastMessage, String timeStamp, String senderId, String senderName, String conversationId, String receiverId) {
        this.lastMessage = lastMessage;
        this.timeStamp = timeStamp;
        this.senderId = senderId;
        this.senderName = senderName;
        this.conversationId = conversationId;
        this.receiverId = receiverId;
    }

    public Conversation() {
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
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

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "lastMessage='" + lastMessage + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", senderName='" + senderName + '\'' +
                ", conversationId='" + conversationId + '\'' +
                '}';
    }
}
