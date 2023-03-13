package com.example.spabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spabookingapp.ConnectionDB.UserRoomDB;
import com.example.spabookingapp.Entities.User;

public class SignupActivity extends AppCompatActivity {

    EditText inputPhoneNumber;
    EditText inputPassword;
    EditText inputConfirmPassword;
    Button btnSubmitSignup;
    private UserRoomDB userRoomDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);
        userRoomDB = UserRoomDB.getDatabase(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        inputPassword = findViewById(R.id.inputPassword);
        inputPhoneNumber = findViewById(R.id.inputPhoneNumber);
        btnSubmitSignup = findViewById(R.id.btnSubmitSignup);

        btnSubmitSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = userRoomDB.userDAO().checkExist(inputPhoneNumber.getText().toString());
                if (user != null) {
                    Toast.makeText(SignupActivity.this, "Số điện thoại tồn tại", Toast.LENGTH_SHORT).show();
                }else{
                    if (inputConfirmPassword.getText().toString().equals(inputPassword.getText().toString())) {
                            User newUser = new User(inputPassword.getText().toString(),inputPhoneNumber.getText().toString(),0);
                            userRoomDB.userDAO().insertAll(newUser);
                            Toast.makeText(SignupActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            finish();

                    }else{
                        Toast.makeText(SignupActivity.this, "Nhập lại mật khẩu cho khớp", Toast.LENGTH_SHORT).show();
                    }
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