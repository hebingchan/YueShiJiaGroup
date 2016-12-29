package com.a1000phone.n5thgroup.yueshijiagroup.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.a1000phone.n5thgroup.yueshijiagroup.R;
import com.a1000phone.n5thgroup.yueshijiagroup.adapter.BannerAdapter;
import com.a1000phone.n5thgroup.yueshijiagroup.listener.BannerListener;
import com.a1000phone.n5thgroup.yueshijiagroup.view.Banner;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.banner)
    Banner mBanner;
    @InjectView(R.id.dot_group)
    RadioGroup mDotGroup;
    private String[] urls = {
            "http://cover.17sysj.com/video_20161228115827735.jpg",
            "http://cover.17sysj.com/video_20161228111728450.jpg",
            "http://apps.ifeimo.com/Public/Uploads/Focuse/Flag/5857bd13b830f.jpg",
            "http://cover.17sysj.com/video_201612270955331083.jpg"
    };
    private BannerAdapter mBannerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        mBannerAdapter = new BannerAdapter(this, urls, mBanner, mDotGroup);
        mBanner.setAdapter(mBannerAdapter);
        mBanner.addOnPageChangeListener(new BannerListener(mDotGroup));
        mBanner.setAutoScroll(true);
        mBanner.setOnItemClickListener(new Banner.OnItemClickListener() {
            @Override
            public void onItemClick() {
                Log.e("MainActivity", mBanner.getCurrentItem() + "");
            }
        });
    }
}
