package com.phamhoan.navigationbar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phamhoan.navigationbar.R;
import com.phamhoan.navigationbar.adapter.ViewPagerAdapter;


public class ViewpagerTablayoutFragment extends Fragment {
    private View rootView;
    private ViewPager pager;
    private TabLayout tablayout;
    private ViewPagerAdapter pagerAdapter;
    int count1 = 0;
    int count2 = 0;
    int count3 = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_viewpager_tablayout,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        pager = rootView.findViewById(R.id.view_pager);
        tablayout = rootView.findViewById(R.id.tab_layout);
       FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        pagerAdapter = new ViewPagerAdapter(fragmentManager);
        pager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(pager);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        count1++;
                        FragmentOne fragmentOne = (FragmentOne) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + position);
                        fragmentOne.setCount(count1);
                        break;
                    case 1:
                        count2++;
                        FragmentTwo fragmentTwo = (FragmentTwo) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + position);
                        fragmentTwo.setCount(count2);
                        break;
                    case 2:
                        count3++;
                        FragmentThree fragmentThree = (FragmentThree) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + position);
                        fragmentThree.setCount(count3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
