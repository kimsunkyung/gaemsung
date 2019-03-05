package com.example.user.gamsung;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.user.gamsung.Home.HomeFragment;
import com.example.user.gamsung.ImgSearch.ImgFragment;
import com.example.user.gamsung.Mypage.MypageFragment;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {


    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return HomeFragment.createFragment();

            case 1:
                return ImgFragment.createFragment();

            case 2:
                return MypageFragment.createFragment();
            case 3:
                return SettingFragment.createFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }



}
