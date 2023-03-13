package com.example.spabookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spabookingapp.ConnectionDB.UserRoomDB;
import com.example.spabookingapp.Entities.User;


public class LoginActivity extends AppCompatActivity {

    protected UserRoomDB userRoomDB;

    protected Button btnSubmitLogin;
    protected EditText inputPhoneNumber;
    protected EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Them mot doan code moi
        setContentView(R.layout.layout_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userRoomDB = UserRoomDB.getDatabase(this);
        btnSubmitLogin = findViewById(R.id.btnSubmitLogin);
        inputPassword= findViewById(R.id.inputPassword);
        inputPhoneNumber=findViewById(R.id.inputPhoneNumber);

        btnSubmitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = userRoomDB.userDAO().login(inputPhoneNumber.getText().toString(),inputPassword.getText().toString());

                if (user == null) {
                    Toast.makeText(getApplicationContext(), "Sai số điện thoại hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences sharedPreferences = getSharedPreferences("Account",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("uid",String.valueOf(user.getUid()));
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                    // tam thoi khi login xong move to user profile
                    Intent intent = new Intent(getApplicationContext(),UserProfileActivity.class);
                    startActivity(intent);

                }
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back arrow click event
        if (item.getItemId() == android.R.id.home) {
            // Finish the current activity and go back to the previous one
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}