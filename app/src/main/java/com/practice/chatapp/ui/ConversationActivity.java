package com.practice.chatapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.practice.chatapp.R;
import com.practice.chatapp.Utils.PreferenceManger;
import com.practice.chatapp.adapters.ConversationAdapter;
import com.practice.chatapp.databinding.ActivityChatBinding;
import com.practice.chatapp.model.Conversation;
import com.practice.chatapp.viewmodel.ConversationViewModel;

import java.util.ArrayList;
import java.util.List;

public class ConversationActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private ConversationAdapter adapter;
    private List<Conversation> conversationList;
    private ConversationViewModel viewModel;
    private PreferenceManger preferenceManger;
    private String TAG = "ConversationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        try {
            initWidgets();
            clickListeners();
            fetchData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_logout:
                preferenceManger.logoutData();
                startActivity(new Intent(this, SignInActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clickListeners() {
        binding.btnFloating.setOnClickListener(view -> {
            startActivity(new Intent(this, UsersActivity.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return true;
    }

    private void initWidgets() {
        preferenceManger = new PreferenceManger(this);
        conversationList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(ConversationViewModel.class);
        adapter = new ConversationAdapter(this, conversationList);
        binding.recycleview.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleview.setAdapter(adapter);

    }

    private void fetchData() {
        viewModel.getAllConversationFromFireBase(preferenceManger.getUserId());
        conversationList.clear();
        viewModel.getConversation().observe(this, conversations -> {
            Log.e(TAG, "conversation " + conversations);
            conversationList.addAll(conversations);
            adapter.notifyDataSetChanged();
        });
        viewModel.getErrorMessage().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }
}