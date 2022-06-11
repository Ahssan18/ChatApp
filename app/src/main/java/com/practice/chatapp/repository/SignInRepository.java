package com.practice.chatapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.practice.chatapp.model.User;

public class SignInRepository {
    private String TAG = "SignInRepository";
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private MutableLiveData<User> userLiveData;
    private MutableLiveData<String> error;

    public SignInRepository() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userLiveData = new MutableLiveData<>();
        error = new MutableLiveData<>();
    }

    public void signInUser(String email, String pass) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success");
                FirebaseUser user = auth.getCurrentUser();
                User user1 = new User(user.getEmail(), user.getUid(), user.getEmail().split("@")[0]);
                database.getReference().child("Users").child(user.getUid()).setValue(user1);
                userLiveData.postValue(user1);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                error.postValue(task.getException().toString());
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
