package net.hailm.storylab10.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import net.hailm.storylab10.R;

import org.w3c.dom.Text;

/**
 * Created by hai_l on 31/10/2017.
 */

public class StoryDetailActivity extends AppCompatActivity {
    private ImageView imgAvatar;
    private TextView txtName;
    private TextView txtAuthor;
    private TextView txtType;
    private TextView txtStatus;
    private TextView txtChapter;
    private TextView txtUpdateDate;
    private TextView txtUploadDate;
    private TextView txtContents;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        initializeComponents();

    }

    private void initializeComponents() {
        imgAvatar = findViewById(R.id.img_avatar);
        txtName = findViewById(R.id.txt_name);
        txtAuthor = findViewById(R.id.txt_author);
        txtType = findViewById(R.id.txt_type);
        txtStatus = findViewById(R.id.txt_status);
        txtChapter = findViewById(R.id.txt_chapters);
        txtUpdateDate = findViewById(R.id.txt_update_date);
        txtUploadDate = findViewById(R.id.txt_upload_date);
        txtContents = findViewById(R.id.txt_contents);


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Key.DATA);
        imgAvatar.setImageResource(bundle.getInt(Key.IMG_AVATAR));
        txtName.setText(bundle.getString(Key.NAME));
        txtAuthor.setText("Tác giả: " + bundle.getString(Key.AUTHOR));
        txtType.setText("Thể loại:" + bundle.getString(Key.TYPE));
        txtStatus.setText("Trạng thái: " + bundle.getString(Key.STATUS));
        txtChapter.setText("Số chương:" + bundle.getInt(Key.CHAPTER));
        txtUpdateDate.setText("Ngày up:" + bundle.getString(Key.UPDATE_DATE));
        txtUploadDate.setText("Ngày cập nhật: " + bundle.getString(Key.UPLOAD_DATE));
        txtContents.setText(bundle.getString(Key.CONTENTS));
    }
}
