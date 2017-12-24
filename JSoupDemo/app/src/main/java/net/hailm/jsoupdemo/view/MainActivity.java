package net.hailm.jsoupdemo.view;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.hailm.jsoupdemo.R;
import net.hailm.jsoupdemo.adapter.StoriesAdapter;
import net.hailm.jsoupdemo.manager.StoryManager;
import net.hailm.jsoupdemo.model.Story;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "data";
    private static final int MESSAGE_DATA = 1000;
    private StoryManager storyManager;
    private Handler handler;
    private ProgressDialog progressDialog;

    private RecyclerView rcvStories;
    private List<Story> stories;
    private StoriesAdapter storiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        getOnlineStories();
//        getContentStories();

    }

    private void initializeComponents() {
        stories = new ArrayList<>();
        rcvStories = findViewById(R.id.rcv_stories);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rcvStories.setLayoutManager(llm);

        initProgressDialog();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what) {
                    case MESSAGE_DATA:
                        stories = (List<Story>) msg.obj;
                        storiesAdapter = new StoriesAdapter(stories, getBaseContext());

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                rcvStories.setAdapter(storiesAdapter);
                            }
                        }, 3000);
                        break;

                    default:
                        break;
                }
            }
        };

//        storiesAdapter = new StoriesAdapter(stories, this);
//        rcvStories.setAdapter(storiesAdapter);

    }

    private void getOnlineStories() {
        storyManager = new StoryManager();
        storyManager.getOnlineStory(new StoryManager.OnStoryFetchListener() {
            @Override
            public void onResponse(List<Story> data, Exception e) {
                if (e != null) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG, "onResponse: " + data);

                Message msg = new Message();
                msg.what = MESSAGE_DATA;
                msg.obj = data;

                handler.sendMessage(msg);
            }
        });
    }

    private void getContentStories() {
        storyManager = new StoryManager();
        storyManager.getStoriesDetail("http://truyencotich.vn/truyen-dan-gian/su-tich-cao-lanh.html", new StoryManager.OnContentFetchListener() {
            @Override
            public void onResponse(StringBuilder content, Exception e) {
                if (e != null) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d(TAG, "onResponse: " + content);
            }
        });
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Retrieving data");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }
}
