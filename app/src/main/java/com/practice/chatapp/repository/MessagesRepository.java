package com.practice.chatapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.practice.chatapp.model.Conversation;
import com.practice.chatapp.model.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MessagesRepository {
    private String TAG = "MessagesRepository";
    private FirebaseDatabase database;
    private MutableLiveData<List<Message>> messagesLivedata;
    private List<Message> messageList;
    private MutableLiveData<String> errorMessage;

    public MessagesRepository() {
        database = FirebaseDatabase.getInstance();
        messageList = new ArrayList<>();
        errorMessage = new MutableLiveData<>();
        messagesLivedata = new MutableLiveData<>();
    }

    public void sendMessage(String message, String conversationId, String senderId, String senderName, String receiverId) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        database.getReference().child("messages").child(conversationId)
                .push().setValue(new Message(message, senderId, strDate));
        DatabaseReference reference = database.getReference().child("conversations");
        Conversation conversation = new Conversation(message, strDate, senderId, senderName, conversationId, receiverId);
        reference.child(senderId).
                child(conversationId).setValue(conversation);
        reference.child(receiverId).
                child(conversationId).setValue(conversation);
    }

    public void getMessagesFromFirebase(String conversationId) {
        try {
            database.getReference().child("messages").child(conversationId)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            messageList.clear();
                            try {
                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                    Message message = snapshot1.getValue(Message.class);
                                    Log.e(TAG, "message " + message);
                                    messageList.add(message);

                                }
                                messagesLivedata.postValue(messageList);
                            } catch (
                                    Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            errorMessage.postValue(error.getMessage());
                        }
                    });
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    public LiveData<List<Message>> getMessages() {
        return messagesLivedata;
    }

    public LiveData<String> getError() {
        return errorMessage;
    }

}
