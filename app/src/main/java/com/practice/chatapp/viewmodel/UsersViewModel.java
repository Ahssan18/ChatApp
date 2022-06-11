package com.practice.chatapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.practice.chatapp.model.User;
import com.practice.chatapp.repository.UsersRepository;

import java.util.List;

public class UsersViewModel extends ViewModel {
    private UsersRepository repository;
    private LiveData<List<User>> userList;
    private LiveData<String> errorMessage;

    public UsersViewModel() {
        repository = new UsersRepository();
        userList = repository.getAllUsers();
        errorMessage = repository.getError();
    }

    public void getUserFromFirebase() {
        repository.getUser();
    }

    public LiveData<List<User>> getUserList() {
        return userList;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
