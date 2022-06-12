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
import java.util.Map;

public class ConversationRepository {
    private MutableLiveData<List<Conversation>> conversationData;
    private MutableLiveData<String> errorMessage;
    private FirebaseDatabase database;
    private List<Conversation> conversationList;
    private String TAG = "ConversationRepository";

    public ConversationRepository() {
        conversationData = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        database = FirebaseDatabase.getInstance();
        conversationList = new ArrayList<>();
    }

    public void getConversationFromFirebase(String id) {
        database.getReference().child("conversations").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                conversationList.clear();
                try {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        Map<String, String> map = (Map<String, String>) snapshot1.getValue();
                        conversationList.add(new Conversation(map.get("lastMessage"), map.get("timeStamp"), map.get("senderId"), map.get("senderName"), map.get("conversationId"), map.get("receiverId")));
                    }
                    conversationData.postValue(conversationList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

