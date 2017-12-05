package net.hailm.ailatrieuphuapp.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import net.hailm.ailatrieuphuapp.R;
import net.hailm.ailatrieuphuapp.dialog.NoticeDialog;
import net.hailm.ailatrieuphuapp.fragments.HomeFragment;


public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bg_circle_rotate);
        animation.setDuration(3000);

        findViewById(R.id.load).startAnimation(animation);

        homeFragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.frame_main, homeFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        final NoticeDialog noticeDialog = new NoticeDialog(MainActivity.this);
        noticeDialog.setCancelable(true);
        noticeDialog.setNotification("Bạn có muốn thoát trò chơi?", "Đồng ý", "Hủy", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_ok) {
                    finish();
                }
                noticeDialog.dismiss();
            }
        });
        noticeDialog.show();
    }
}
