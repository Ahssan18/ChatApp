package com.practice.chatapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.practice.chatapp.model.User;
import com.practice.chatapp.repository.SignInRepository;

public class SignInViewModel extends ViewModel {
    private SignInRepository signInRepository;
    private LiveData<User> userLiveData;
    private LiveData<String> error;

    public SignInViewModel() {
        signInRepository = new SignInRepository();
        error = signInRepository.getError();
        userLiveData = signInRepository.getUser();
    }

    public void signIn(String email, String pass) {
        signInRepository.signInUser(email, pass);
    }

    public LiveData<User> getUserData() {
        return userLiveData;
    }

    public LiveData<String> getError() {
        return error;
    }
}
