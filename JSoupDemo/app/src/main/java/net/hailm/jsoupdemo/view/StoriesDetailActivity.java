package net.hailm.jsoupdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import net.hailm.jsoupdemo.R;
import net.hailm.jsoupdemo.manager.StoryManager;

/**
 * Created by hai_l on 01/12/2017.
 */

public class StoriesDetailActivity extends AppCompatActivity {

    private static final String TAG = "Data";
    private static final int MESSAGE_CONTENT = 1000;
    private StoryManager storyManager;
    private Handler handler;

    private TextView txtArticle;
    private String articleUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_stories);

        initializeComponents();
        getContentStories();
    }


    private void initializeComponents() {
        txtArticle = findViewById(R.id.txt_article);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what) {
                    case MESSAGE_CONTENT:
                        StringBuilder obj = (StringBuilder) msg.obj;
                        txtArticle.setText(obj);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void getContentStories() {
        storyManager = new StoryManager();

        Intent intent = getIntent();
        articleUrl = intent.getStringExtra("ARTICLE_URL");

        storyManager.getStoriesDetail(articleUrl, new StoryManager.OnContentFetchListener() {
            @Override
            public void onResponse(StringBuilder content, Exception e) {
                if (e != null) {
                    Toast.makeText(StoriesDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }

                Message msg = new Message();
                msg.what = MESSAGE_CONTENT;
                msg.obj = content;
                handler.sendMessage(msg);
            }
        });
    }


}
