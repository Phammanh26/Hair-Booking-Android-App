package com.example.spabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spabookingapp.ConnectionDB.UserRoomDB;
import com.example.spabookingapp.Entities.User;
import com.squareup.picasso.Picasso;

public class UserProfileActivity extends AppCompatActivity {

    EditText inputFullname;
    EditText inputPhoneNumber;
    EditText inputEmail;
    ImageView inputImage;
    Button btnSubmitLogout;
    TextView btnEditProfile;
    private UserRoomDB userRoomDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_userprofile);
        inputImage = findViewById(R.id.roundedImageView);
        inputFullname = findViewById(R.id.innputFullname);
        inputPhoneNumber = findViewById(R.id.inputPhoneNumber);
        inputEmail = findViewById(R.id.inputEmail);
        btnSubmitLogout = findViewById(R.id.btnSubmitLogout);
        btnEditProfile = findViewById(R.id.editProfile);

        userRoomDB = UserRoomDB.getDatabase(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SharedPreferences pref = getSharedPreferences("Account",MODE_PRIVATE);

        User user = userRoomDB.userDAO().findByID(pref.getString("uid",null));

        if (user != null) {
            inputFullname.setHint(user.getFullname());
            inputPhoneNumber.setHint(user.getPhoneNumber());
            inputEmail.setHint(user.getEmail());
            if (user.getAvatar() != null && !user.getAvatar().equals("")) {

                Log.d("image:",user.getAvatar());
                Picasso.with(getApplicationContext())
                        .load(user.getAvatar())
                        .error(R.drawable.avatar)
                        .into(inputImage);
            }
        }


        btnSubmitLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getApplicationContext(),StartingAppActivity.class);
                startActivity(intent);
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditProfileActivity.class);
                startActivity(intent);
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