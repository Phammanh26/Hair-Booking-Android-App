package com.example.spabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.spabookingapp.ConnectionDB.UserRoomDB;
import com.example.spabookingapp.Entities.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    protected UserRoomDB userRoomDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Them mot doan code moi
        setContentView(R.layout.layout_login);
        userRoomDB = UserRoomDB.getDatabase(this);
        Log.i("show: ","123");
        List<User> users = userRoomDB.userDAO().getAll();
        for (int i = 0; i < users.size(); i++) {
            Log.i("show: ",users.get(i).getFullname());
        }
        // Them mot doan code moi
        //aaa
    }
}