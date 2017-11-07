package net.hailm.recyclerviewlab11.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import net.hailm.recyclerviewlab11.R;
import net.hailm.recyclerviewlab11.adapter.StoriesViewPagerAdapter;
import net.hailm.recyclerviewlab11.model.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai_l on 06/11/2017.
 */

public class StoriesDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgBack;
    private ViewPager vpgStories;
    private List<Story> stories;
    private StoriesViewPagerAdapter storiesViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_stories_detail);

        initializeComponents();
        registerListener();
    }

    private void initializeComponents() {
        imgBack = findViewById(R.id.img_back);
        vpgStories = findViewById(R.id.vpg_story);

        Intent intent = getIntent();
        stories = new ArrayList<>();
        //lấy dữ liệu được chuyển đến từ recycleView thông qua intent
        stories = (List<Story>) intent.getSerializableExtra("LIST");
        int pos = (int) intent.getSerializableExtra("POS");// day

        storiesViewPagerAdapter = new StoriesViewPagerAdapter(stories, this);
        vpgStories.setAdapter(storiesViewPagerAdapter);
        vpgStories.setCurrentItem(pos);
    }

    private void registerListener() {
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;

            default:
                break;
        }
    }
}
