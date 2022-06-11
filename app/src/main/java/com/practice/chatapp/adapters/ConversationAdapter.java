package com.practice.chatapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.chatapp.databinding.CustomConversationBinding;
import com.practice.chatapp.model.Conversation;
import com.practice.chatapp.ui.InboxActivity;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.CustomConversation> {
    private Context context;
    private List<Conversation> conversationList;

    public ConversationAdapter(Context context, List<Conversation> conversationList) {
        this.context = context;
        this.conversationList = conversationList;
    }

    @NonNull
    @Override
    public CustomConversation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomConversationBinding binding = CustomConversationBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CustomConversation(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomConversation holder, int position) {
        Conversation conversation = conversationList.get(position);
        holder.binding.tvUserName.setText(conversation.getSenderName());
        holder.binding.tvLastMessage.setText(conversation.getLastMessage());
        holder.binding.tvTimeStamp.setText(conversation.getTimeStamp());
        holder.binding.constraintUser.setOnClickListener(view -> {
            Intent intent = new Intent(context, InboxActivity.class);
            intent.putExtra("name", conversationList.get(position).getSenderName());
            intent.putExtra("conversationId", "");
            context.startActivity(intent);


        });

    }

    @Override
    public int getItemCount() {
        return conversationList.size();
    }

    public class CustomConversation extends RecyclerView.ViewHolder {
        private CustomConversationBinding binding;

        public CustomConversation(@NonNull CustomConversationBinding binding) {
            super(binding.getRoot());
        }
    }
}
