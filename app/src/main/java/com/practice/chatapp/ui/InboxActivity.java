package com.practice.chatapp.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.practice.chatapp.Utils.PreferenceManger;
import com.practice.chatapp.adapters.InboxAdapter;
import com.practice.chatapp.databinding.ActivityInboxBinding;
import com.practice.chatapp.model.Message;
import com.practice.chatapp.viewmodel.MessagesViewModel;

import java.util.List;

public class InboxActivity extends AppCompatActivity {
    private ActivityInboxBinding binding;
    private InboxAdapter adapter;
    private List<Message> messageList;
    private String name, conversationId;
    private MessagesViewModel viewModel;
    private PreferenceManger preferenceManger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInboxBinding.inflate(getLayoutInflater());
        initWidgets();
        setData();
        clickLister();
        observeData();
        setContentView(binding.getRoot());
    }

    private void setData() {
        binding.tvUserName.setText(name);
    }

    private void initWidgets() {

        conversationId = getIntent().getStringExtra("conversationId");
        name = getIntent().getStringExtra("name");
        preferenceManger = new PreferenceManger(this);
        viewModel = new ViewModelProvider(this).get(MessagesViewModel.class);
        adapter = new InboxAdapter(messageList, this);
        binding.recycleMessages.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleMessages.setAdapter(adapter);
    }

    private void clickLister() {
        binding.btnSend.setOnClickListener(view -> {
            String message = binding.etMessage.getText().toString();
            if (!message.isEmpty()) {
                viewModel.sendMessage(message, conversationId, preferenceManger.getUserId());
            }
        });
    }

    private void observeData() {
        viewModel.getMessages().observe(this, messagesList -> {
            messageList.addAll(messagesList);
            adapter.notifyDataSetChanged();
        });

        viewModel.getErrorMessage().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }
}