package com.hailm.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_PROFILE_EDITOR = 100;  //REQUEST_CODE + màn hình
    private ImageView imgAvatar;
    private TextView tvEmail;
    private TextView tvFullname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeComponents();
        registerListener();
    }


    private void initializeComponents() {
        imgAvatar = (ImageView) findViewById(R.id.img_avatar);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvFullname = (TextView) findViewById(R.id.tv_full_name);
    }

    private void registerListener() {
        imgAvatar.setOnClickListener(this);
        tvEmail.setOnClickListener(this);
        tvFullname.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_avatar:
                demoIntentImplicit();
                break;

            case R.id.tv_email:
                showEditorScreen();
                break;

            default:
                break;
        }

    }

    private void demoIntentImplicit() {
        // IntentImplicit là intent dùng để
        // kich hoat các UD của hệ thống
        // hoặc của bên thứ 3

        // Bật brower
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("http://tinhte.vn"));
//        startActivity(intent);


        // Bật Call
//        Intent intent1 = new Intent(Intent.ACTION_VIEW);
//        intent1.setData(Uri.parse("tel: 09990"));
//        startActivity(intent1);

        // Bật galary
        //
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivity(intent);


    }

    // Buoc 1: Bat man hinh chinh sua
    private void showEditorScreen() {
        Intent intent = new Intent(this, ProfileActivity.class);
        // startActivity(intent);
        startActivityForResult(intent, REQUEST_CODE_PROFILE_EDITOR);
    }

    // Bước 3: Nhận kq trả về

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_PROFILE_EDITOR:
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        User user = (User) data.getSerializableExtra("KEY_USER");
                        String email = user.getEmail();
                        tvEmail.setText(email);
                    }
                }
                break;

            default:
                break;
        }

    }
}
