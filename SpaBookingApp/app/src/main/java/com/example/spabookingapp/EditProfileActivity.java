package com.example.spabookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class EditProfileActivity extends AppCompatActivity {

    EditText inputFullname;
    EditText inputEmail;
    EditText inputDOB;
    Button btnSubmitUpdate;
    TextView clearImage;
    ImageView imageView;
    private UserRoomDB userRoomDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_editprofile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        userRoomDB = UserRoomDB.getDatabase(this);


        inputDOB = findViewById(R.id.inputDOB);
        inputFullname = findViewById(R.id.innputFullname);
        inputEmail = findViewById(R.id.inputEmail);
        btnSubmitUpdate = findViewById(R.id.btnSubmitUpdate);
        clearImage = findViewById(R.id.clearAvatar);
        imageView = findViewById(R.id.roundedImageView);



        SharedPreferences pref = getSharedPreferences("Account",MODE_PRIVATE);

        User user = userRoomDB.userDAO().findByID(pref.getString("uid",null));

        if (user != null) {
            inputFullname.setHint(user.getFullname());
            inputEmail.setHint(user.getEmail());
            inputDOB.setHint(user.getAvatar());
            Picasso.with(getApplicationContext())
                    .load(user.getAvatar())
                    .error(R.drawable.avatar)
                    .into(imageView);
        }else{
            Intent intent = new Intent(getApplicationContext(),StartingAppActivity.class);
            startActivity(intent);
        }


        btnSubmitUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputDOB.getText().toString().equals("")) {
                    user.setAvatar(inputDOB.getText().toString());
                }
                if (!inputEmail.getText().toString().equals("")) {
                    user.setEmail(inputEmail.getText().toString());
                }
                if (!inputFullname.getText().toString().equals("")) {
                    user.setFullname(inputFullname.getText().toString());
                }
                userRoomDB.userDAO().updateUser(user);
                Toast.makeText(EditProfileActivity.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra("uid",String.valueOf(user.getUid()));
                setResult(Activity.RESULT_OK,intent);
                finish();

            }
        });

        clearImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDOB.setHint("");
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