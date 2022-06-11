package com.practice.chatapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.practice.chatapp.model.Conversation;
import com.practice.chatapp.repository.ConversationRepository;

import java.util.List;

public class ConversationViewModel extends ViewModel {
    private ConversationRepository repository;
    private LiveData<List<Conversation>> conversationData;
    private LiveData<String> errorMessage;

    public ConversationViewModel() {
        repository = new ConversationRepository();
        conversationData = repository.getConversations();
        errorMessage = repository.getError();
    }

    public void getAllConversationFromFireBase(String id) {
        repository.getConversationFromFirebase(id);
    }

    public LiveData<List<Conversation>> getConversation() {

        return conversationData;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
