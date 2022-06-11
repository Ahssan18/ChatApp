package com.practice.chatapp.repository;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.MutableData;
import com.practice.chatapp.model.User;

import java.util.concurrent.Executor;

public class RegisterRepository {
    private FirebaseAuth mAuth;
    private String TAG = "RegisterRepository";
    private MutableLiveData<User> userLiveData;
    private MutableLiveData<String> error;


    public RegisterRepository() {
        mAuth = FirebaseAuth.getInstance();
        userLiveData = new MutableLiveData<>();
        error = new MutableLiveData<>();

    }

    public void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        userLiveData.postValue(new User(user.getEmail(), user.getUid(), user.getEmail().split("@")[0]));
                    } else {
                        error.postValue(String.valueOf(task.getException()));
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
//                        Toast.makeText(context, "Authentication failed.",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public LiveData<User> getUser() {
        return userLiveData;
    }

    public LiveData<String> getError() {
        return error;
    }
}
