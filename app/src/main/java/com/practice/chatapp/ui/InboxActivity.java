package com.practice.chatapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.practice.chatapp.Utils.PreferenceManger;
import com.practice.chatapp.adapters.InboxAdapter;
import com.practice.chatapp.databinding.ActivityInboxBinding;
import com.practice.chatapp.model.Message;
import com.practice.chatapp.viewmodel.MessagesViewModel;

import java.util.ArrayList;
import java.util.List;

public class InboxActivity extends AppCompatActivity {
    private ActivityInboxBinding binding;
    private InboxAdapter adapter;
    private List<Message> messageList;
    private String name, conversationId;
    private MessagesViewModel viewModel;
    private PreferenceManger preferenceManger;
    private String TAG = "InboxActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInboxBinding.inflate(getLayoutInflater());
        initWidgets();
        setData();
        clickLister();
        observeData();
        try {
            setContentView(binding.getRoot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData() {
        try {
            binding.tvUserName.setText(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initWidgets() {
        try {
            getSupportActionBar().hide();
            messageList = new ArrayList<>();
            conversationId = getIntent().getExtras().getString("conversationId");
            name = getIntent().getExtras().getString("name");
            preferenceManger = new PreferenceManger(this);
            viewModel = new ViewModelProvider(this).get(MessagesViewModel.class);
            adapter = new InboxAdapter(messageList, this);
            binding.recycleMessages.setLayoutManager(new LinearLayoutManager(this));
            binding.recycleMessages.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clickLister() {
        binding.ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
        binding.btnSend.setOnClickListener(view -> {
            String message = binding.etMessage.getText().toString();
            if (!message.isEmpty()) {
                viewModel.sendMessage(message, conversationId, preferenceManger.getUserId(), preferenceManger.getUserName());
            }
        });
    }

    private void observeData() {
        viewModel.getMessagesFromFirebase(conversationId);
        messageList.clear();
        viewModel.getMessages().observe(this, messagesList -> {
            Log.e(TAG, "messagesList " + messagesList);
            binding.etMessage.setText("");
            messageList.addAll(messagesList);
            adapter.notifyDataSetChanged();
        });

        viewModel.getErrorMessage().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }
}