package com.practice.chatapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.chatapp.Utils.PreferenceManger;
import com.practice.chatapp.databinding.CustomUsersBinding;
import com.practice.chatapp.model.User;
import com.practice.chatapp.ui.InboxActivity;

import java.util.List;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.CustomUser> {
    private List<User> usersList;
    private Context context;
    private PreferenceManger preferenceManger;

    public AllUsersAdapter(List<User> users, Context context) {
        this.usersList = users;
        this.context = context;
        preferenceManger = new PreferenceManger(context);
    }

    @NonNull
    @Override
    public CustomUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        com.practice.chatapp.databinding.CustomUsersBinding binding = CustomUsersBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CustomUser(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomUser holder, int position) {
        holder.binding.tvUserName.setText(usersList.get(position).getName());
        holder.binding.constraintUser.setOnClickListener(view -> {
            Intent intent = new Intent(context, InboxActivity.class);
            intent.putExtra("name", usersList.get(position).getName());
            String conversationid = preferenceManger.getUserId() + usersList.get(position).getId();
            intent.putExtra("conversationId", conversationid);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class CustomUser extends RecyclerView.ViewHolder {
        private CustomUsersBinding binding;

        public CustomUser(@NonNull com.practice.chatapp.databinding.CustomUsersBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
