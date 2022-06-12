package com.practice.chatapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.chatapp.Utils.PreferenceManger;
import com.practice.chatapp.databinding.CustomMessageLeftBinding;
import com.practice.chatapp.databinding.CustomMessageRightBinding;
import com.practice.chatapp.model.Message;

import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.CustomMessage> {
    private List<Message> messageList;
    private Context context;
    private PreferenceManger preferenceManger;
    private String TAG = "InboxAdapter";

    public InboxAdapter(List<Message> messageList, Context context) {
        this.messageList = messageList;
        this.context = context;
        preferenceManger = new PreferenceManger(context);
    }

    @NonNull
    @Override
    public CustomMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            CustomMessageRightBinding bindingRight = CustomMessageRightBinding.inflate(LayoutInflater.from(context), parent, false);
            return new CustomMessage(bindingRight);
        } else {
            CustomMessageLeftBinding bindingLeft = CustomMessageLeftBinding.inflate(LayoutInflater.from(context), parent, false);
            return new CustomMessage(bindingLeft);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CustomMessage holder, int position) {
        if (messageList.get(position).getSenderId().equals(preferenceManger.getUserId())) {
            holder.bindingRight.tvMessage.setText(messageList.get(position).getMessageTitle());
        } else {
            holder.bindingLeft.tvMessage.setText(messageList.get(position).getMessageTitle());

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (messageList.get(position).getSenderId().equals(preferenceManger.getUserId()))
            return 0;
        else
            return 1;
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount :" + messageList.size());
        return messageList.size();
    }


    public class CustomMessage extends RecyclerView.ViewHolder {
        private CustomMessageLeftBinding bindingLeft;
        private CustomMessageRightBinding bindingRight;

        public CustomMessage(@NonNull CustomMessageLeftBinding binding) {
            super(binding.getRoot());
            this.bindingLeft = binding;
        }

        public CustomMessage(@NonNull CustomMessageRightBinding binding) {
            super(binding.getRoot());
            this.bindingRight = binding;
        }
    }
}
