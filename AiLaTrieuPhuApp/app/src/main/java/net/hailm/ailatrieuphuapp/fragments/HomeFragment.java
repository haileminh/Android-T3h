package net.hailm.ailatrieuphuapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import net.hailm.ailatrieuphuapp.R;
import net.hailm.ailatrieuphuapp.activity.PlayerActivity;

/**
 * Created by hai_l on 21/11/2017.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView btnPlay;
    private ImageView btnZalo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents();
    }

    private void initializeComponents() {
        Animation animRotate = AnimationUtils.loadAnimation(getContext(), R.anim.bg_circle_rotate);
        view.findViewById(R.id.bg_circle_anim).setAnimation(animRotate);

        btnPlay = view.findViewById(R.id.btn_play);
        btnZalo = view.findViewById(R.id.btn_zalo);

        btnZalo.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                Intent intentPlayer = new Intent(getContext(), PlayerActivity.class);
                startActivity(intentPlayer);
                break;

            case R.id.btn_zalo:
                break;

            default:
                break;
        }
    }
}
