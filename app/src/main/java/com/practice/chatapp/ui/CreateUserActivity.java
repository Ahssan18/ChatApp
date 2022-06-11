package com.practice.chatapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.practice.chatapp.R;
import com.practice.chatapp.databinding.ActivityCreateUserBinding;
import com.practice.chatapp.model.User;
import com.practice.chatapp.viewmodel.RegisterViewModel;

public class CreateUserActivity extends AppCompatActivity {

    private ActivityCreateUserBinding binding;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateUserBinding.inflate(getLayoutInflater());
        initViews();
        clickListener();
        ObserveData();
        setContentView(binding.getRoot());
    }

    private void ObserveData() {
        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Toast.makeText(CreateUserActivity.this, "User is created successfully : " + user.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
    }

    private void clickListener() {
        binding.btnRegister.setOnClickListener(view -> {
            String email = binding.editText.getText().toString().trim();
            String pass = binding.etPassword.getText().toString();
            if (!email.isEmpty()) {
                if (!pass.isEmpty() && pass.length() > 5) {
                    viewModel.createUser(email, pass);
                } else {
                    Toast.makeText(this, "Enter Valid password contain length 5 or greater", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "Enter Valid email", Toast.LENGTH_SHORT).show();

            }
        });
    }
}