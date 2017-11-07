package net.hailm.viewpagerdemo;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import net.hailm.viewpagerdemo.adapter.PhotoApdater;
import net.hailm.viewpagerdemo.model.Photo;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgMenu;
    private DrawerLayout drawerLayout;
    private List<Photo> photos;
    private PhotoApdater photoApdater;
    private ViewPager vpgPhoto;
    private CircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDataPhotos();
        initializeComponents();
        registerListener();
    }


    private void initializeComponents() {
        imgMenu = findViewById(R.id.img_menu);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        indicator = findViewById(R.id.circle_indicator);
        vpgPhoto = findViewById(R.id.vpg_photo);

        // Config so luong page 2 ben
        vpgPhoto.setOffscreenPageLimit(1);

        // Tao adapter
        photoApdater = new PhotoApdater(photos, this);
        vpgPhoto.setAdapter(photoApdater);
        indicator.setViewPager(vpgPhoto);
        vpgPhoto.setCurrentItem(2);
    }

    private void registerListener() {
        imgMenu.setOnClickListener(this);
    }

    private void createDataPhotos() {
        photos = new ArrayList<>();
        photos.add(new Photo(R.drawable.ic_smile_icon1, "Smile 1"));
        photos.add(new Photo(R.drawable.ic_smile_icon2, "Smile 2"));
        photos.add(new Photo(R.drawable.ic_smile_icon3, "Smile 3"));
        photos.add(new Photo(R.drawable.ic_smile_icon4, "Smile 4"));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_menu) {
            drawerLayout.openDrawer(Gravity.START);
        }
    }
}
