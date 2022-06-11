package com.practice.chatapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.practice.chatapp.model.User;
import com.practice.chatapp.repository.RegisterRepository;

public class RegisterViewModel extends ViewModel {
    private RegisterRepository registerRepository;
    private LiveData<User> userLiveData;

    public RegisterViewModel() {
        registerRepository = new RegisterRepository();
        userLiveData = registerRepository.getUser();
    }

    public void createUser(String email, String pass) {
        registerRepository.createUser(email, pass);
    }

    public LiveData<User> getUser() {
        return userLiveData;
    }
}
