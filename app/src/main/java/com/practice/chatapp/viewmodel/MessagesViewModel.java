package com.practice.chatapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.practice.chatapp.model.Message;
import com.practice.chatapp.repository.MessagesRepository;

import java.util.List;

public class MessagesViewModel extends ViewModel {
    private LiveData<List<Message>> messagesLivedata;
    private LiveData<String> errorMessage;
    private MessagesRepository messagesRepository;

    public MessagesViewModel() {
        messagesRepository = new MessagesRepository();
        messagesLivedata = messagesRepository.getMessages();
        errorMessage = messagesRepository.getError();
    }

    public void sendMessage(String message, String conversationId, String senderId) {
        messagesRepository.sendMessage(message, conversationId, senderId);
    }

    public void getMessagesFromFirebase(String conversationid) {
        messagesRepository.getMessagesFromFirebase(conversationid);
    }

    public LiveData<List<Message>> getMessages() {

        return messagesLivedata;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
