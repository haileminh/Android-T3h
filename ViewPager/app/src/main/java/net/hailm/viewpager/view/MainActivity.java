package net.hailm.viewpager.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import net.hailm.viewpager.R;
import net.hailm.viewpager.adapter.PhotoAdapter;
import net.hailm.viewpager.model.Photo;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    private ViewPager vpgPhoto;
    private List<Photo> photos;
    private PhotoAdapter photoAdapter;
    private CircleIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDataPhotos();
        initialzieComponents();
        registerListener();
    }

    private void initialzieComponents() {
        vpgPhoto = findViewById(R.id.vpg_photo);
        circleIndicator = findViewById(R.id.indicator);

        // Config so luong page hai ben
        vpgPhoto.setOffscreenPageLimit(1);

        // Tạo adapter
        photoAdapter = new PhotoAdapter(photos, this);
        vpgPhoto.setAdapter(photoAdapter);
        circleIndicator.setViewPager(vpgPhoto);
        vpgPhoto.setCurrentItem(2, false);


    }

    private void registerListener() {
        vpgPhoto.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Trong qua trinh vuot chuyen trang
            }

            @Override
            public void onPageSelected(int position) {
                // Thoi diem chuyen trang da thanh cong
                Toast.makeText(getBaseContext(), "Page " + (position + 1), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void createDataPhotos() {
        photos = new ArrayList<>();
        photos.add(new Photo(R.drawable.ic_smile_icon1, "Smile 1"));
        photos.add(new Photo(R.drawable.ic_smile_icon2, "Smile 2"));
        photos.add(new Photo(R.drawable.ic_smile_icon3, "Smile 3"));
        photos.add(new Photo(R.drawable.ic_smile_icon4, "Smile 4"));
    }


}
