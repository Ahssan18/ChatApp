package com.practice.chatapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.practice.chatapp.Utils.PreferenceManger;
import com.practice.chatapp.model.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MessagesRepository {
    private FirebaseDatabase database;
    private MutableLiveData<List<Message>> messagesLivedata;
    private List<Message> messageList;
    private MutableLiveData<String> errorMessage;
    private PreferenceManger preferenceManger;

    public MessagesRepository() {
        database = FirebaseDatabase.getInstance();
        errorMessage = new MutableLiveData<>();
        messagesLivedata = new MutableLiveData<>();
    }

    public void sendMessage(String message, String conversationId, String senderId) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        database.getReference().child("messages").child(conversationId)
                .setValue(new Message(message, senderId, strDate));
    }

    public void getMessagesFromFirebase(String conversationId) {
        database.getReference().child("messages").child(conversationId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    messageList.add(snapshot1.getValue(Message.class));
                }
                messagesLivedata.postValue(messageList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                errorMessage.postValue(error.getMessage());
            }
        });
    }

    public LiveData<List<Message>> getMessages() {
        return messagesLivedata;
    }

    public LiveData<String> getError() {
        return errorMessage;
    }

}
