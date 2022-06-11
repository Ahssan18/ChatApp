package com.practice.chatapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.practice.chatapp.model.Conversation;

import java.util.ArrayList;
import java.util.List;

public class ConversationRepository {
    private MutableLiveData<List<Conversation>> conversationData;
    private MutableLiveData<String> errorMessage;
    private FirebaseDatabase database;
    private List<Conversation> conversationList;

    public ConversationRepository() {
        conversationData = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        database = FirebaseDatabase.getInstance();
        conversationList = new ArrayList<>();
    }

    public void getConversationFromFirebase(String id) {
        database.getReference().child("conversations").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    conversationList.add((snapshot.getValue(Conversation.class)));
                }
                conversationData.postValue(conversationList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                errorMessage.postValue(error.getMessage());
            }
        });
    }

    public LiveData<List<Conversation>> getConversations() {
        return conversationData;
    }

    public LiveData<String> getError() {
        return errorMessage;
    }
}
