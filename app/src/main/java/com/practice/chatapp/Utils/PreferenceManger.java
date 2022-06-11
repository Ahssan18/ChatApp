package com.practice.chatapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.practice.chatapp.model.User;

public class PreferenceManger {
    private Context context;

    public PreferenceManger(Context context) {
        this.context = context;
    }

    public SharedPreferences getSharePreference() {
        return context.getSharedPreferences("chat", Context.MODE_PRIVATE);
    }

    public void setUser(User user) {
        SharedPreferences.Editor editor = getSharePreference().edit();
        editor.putString("name", user.getName());
        editor.putString("id", user.getId());
        editor.putString("email", user.getEmail());
        editor.apply();

    }

    public String getUserId() {
        return getSharePreference().getString("id", "-1");
    }
}
