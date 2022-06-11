package com.practice.chatapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.practice.chatapp.Utils.PreferenceManger;
import com.practice.chatapp.databinding.ActivitySignInBinding;
import com.practice.chatapp.viewmodel.SignInViewModel;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private SignInViewModel signInViewModel;
    private PreferenceManger preferenceManger = new PreferenceManger(this);

    @Override
    protected void onStart() {
        super.onStart();
        if (preferenceManger.getUserId().equals("-1")) {
            startActivity(new Intent(this, ConversationActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        initView();
        clickListeners();
        observeData();
        setContentView(binding.getRoot());
    }

    private void observeData() {
        signInViewModel.getUserData().observe(this, user -> {
            Toast.makeText(this, "Successfully Login :" + user.toString(), Toast.LENGTH_SHORT).show();
            preferenceManger.setUser(user);
            startActivity(new Intent(this, ConversationActivity.class));
        });
        signInViewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }

    private void clickListeners() {
        binding.tvRegister.setOnClickListener(view -> {
            startActivity(new Intent(this, CreateUserActivity.class));
        });
        binding.btnLogin.setOnClickListener(view -> {
            String email = binding.editText.getText().toString().trim();
            String pass = binding.etPassword.getText().toString();
            if (!email.isEmpty()) {
                if (!pass.isEmpty() && pass.length() > 5) {
                    signInViewModel.signIn(email, pass);
                } else {
                    Toast.makeText(this, "Enter Valid password contain length 5 or greater", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "Enter Valid email", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initView() {
        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
    }
}