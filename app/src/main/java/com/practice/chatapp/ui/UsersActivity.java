package com.practice.chatapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.practice.chatapp.adapters.AllUsersAdapter;
import com.practice.chatapp.model.User;
import com.practice.chatapp.viewmodel.UsersViewModel;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    private com.practice.chatapp.databinding.ActivityUsersBinding binding;
    private UsersViewModel usersViewModel;
    private AllUsersAdapter allUsersAdapter;
    private List<User> allUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.practice.chatapp.databinding.ActivityUsersBinding.inflate(getLayoutInflater());
        initViews();
        clickListeners();
        observeData();
        setContentView(binding.getRoot());
    }

    private void observeData() {
        usersViewModel.getUserFromFirebase();
        usersViewModel.getUserList().observe(this, userList -> {
            allUserList.addAll(userList);
            allUsersAdapter.notifyDataSetChanged();
        });
        usersViewModel.getErrorMessage().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }

    private void clickListeners() {
    }

    private void initViews() {
        usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        allUserList = new ArrayList<>();
        allUsersAdapter = new AllUsersAdapter(allUserList, this);
        binding.recycleUsers.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleUsers.setAdapter(allUsersAdapter);
    }
}