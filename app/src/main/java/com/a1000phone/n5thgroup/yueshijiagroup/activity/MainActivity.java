package com.a1000phone.n5thgroup.yueshijiagroup.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a1000phone.n5thgroup.yueshijiagroup.R;
import com.a1000phone.n5thgroup.yueshijiagroup.adapter.BannerAdapter;
import com.a1000phone.n5thgroup.yueshijiagroup.view.Banner;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.banner)
    Banner mBanner;
    private List<View> mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        mViews = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            );
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(lp);
            Glide.with(this).load("http://4493bz.1985t.com/uploads/allimg/150127/4-15012G52133.jpg")
                    .placeholder(R.mipmap.ic_launcher).into(imageView);
            mViews.add(imageView);
        }
        mBanner.setAdapter(new BannerAdapter(mViews));
        mBanner.setCurrentItem(Integer.MAX_VALUE >> 1);
    }
}
