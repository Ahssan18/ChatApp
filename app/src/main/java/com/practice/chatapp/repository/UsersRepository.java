package com.practice.chatapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.practice.chatapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    private FirebaseDatabase database;
    private List<User> userList;
    private MutableLiveData<List<User>> listMutableLiveData;
    private MutableLiveData<String> errorMessage;

    public UsersRepository() {
        database = FirebaseDatabase.getInstance();
        userList = new ArrayList<>();
        listMutableLiveData = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
    }

    public void getUser() {
        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot user : snapshot.getChildren()) {
                    userList.add(user.getValue(User.class));
                }
                listMutableLiveData.postValue(userList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                errorMessage.postValue(error.getMessage());
            }
        });
    }

    public LiveData<List<User>> getAllUsers() {
        return listMutableLiveData;
    }

    public LiveData<String> getError() {
        return errorMessage;
    }
}
