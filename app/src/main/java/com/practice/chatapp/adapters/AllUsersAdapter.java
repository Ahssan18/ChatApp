package com.practice.chatapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.chatapp.R;
import com.practice.chatapp.databinding.CustomUsersBinding;
import com.practice.chatapp.model.User;

import java.util.List;
import java.util.Objects;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.CustomUser> {
    private List<User> usersList;
    private Context context;

    public AllUsersAdapter(List<User> users, Context context) {
        this.usersList = users;
        this.context = context;
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
