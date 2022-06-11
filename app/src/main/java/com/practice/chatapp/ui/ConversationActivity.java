package com.practice.chatapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.practice.chatapp.databinding.ActivityChatBinding;

public class ConversationActivity extends AppCompatActivity {

    private ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        initWidgets();
        clickListers();
        fetchData();
        setContentView(binding.getRoot());
    }

    private void clickListers() {
        binding.btnFloating.setOnClickListener(view -> {
            startActivity(new Intent(this, UsersActivity.class));
        });
    }

    private void initWidgets() {

    }

    private void fetchData() {
    }
}