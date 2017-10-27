package com.hailm.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hai_l on 12/10/2017.
 */

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail;
    private Button btnUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        // Set width, height
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        initializeComponents();
        registerListener();
    }

    private void initializeComponents() {
        edtEmail = (EditText) findViewById(R.id.edt_email);
        btnUpdate = (Button) findViewById(R.id.btn_update);
    }

    private void registerListener() {
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                updateProfile();
                break;

            default:
                break;
        }
    }

    private void updateProfile() {
        String email = edtEmail.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(this, "Email không được để trống!", Toast.LENGTH_SHORT).show();
            return;
        }
        // Create a new user
        User user = new User();
        user.setEmail(email);

        // Put user data
        Intent data = new Intent();

        data.putExtra("KEY_USER", user);    //Serializable
        // Buoc 2.
        // Send data
        setResult(Activity.RESULT_OK, data);
        finish(); // ket thuc activity
    }
}