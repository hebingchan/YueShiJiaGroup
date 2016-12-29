package com.a1000phone.n5thgroup.yueshijiagroup.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.a1000phone.n5thgroup.yueshijiagroup.R;
import com.a1000phone.n5thgroup.yueshijiagroup.daobiz.ImageLoadManager;
import com.a1000phone.n5thgroup.yueshijiagroup.view.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hebin on 2016/12/29.
 */

public class BannerAdapter extends PagerAdapter {
    private final Context mContext;
    private final Banner mBanner;
    private final RadioGroup mDotGroup;
    private final String[] mUrls;
    private List<View> mViews;

    public BannerAdapter(final Context context, final String[] urls, final Banner banner, final RadioGroup dotGroup) {
        mViews = new ArrayList<>();
        mContext = context;
        mBanner = banner;
        mDotGroup = dotGroup;
        mUrls = urls;
        init();
    }

    private void init() {
        int itemCount = mUrls.length == 2 ? mUrls.length << 1 : mUrls.length;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        for (int i = 0; i < itemCount; i++) {
            ImageView imageView = new ImageView(mContext);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            );
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoadManager.getInstance().getImageLoader().loadImage(mContext
                    , R.mipmap.ic_launcher, mUrls[i % mUrls.length], imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    mBanner.onItemClick();
                }
            });
            mViews.add(imageView);
            if (i < mUrls.length) {
                RadioButton radioButton = (RadioButton) inflater.inflate(R.layout.dot_btn, mDotGroup, false);
                mDotGroup.addView(radioButton);
                if (i == 0) {
                    radioButton.setChecked(true);
                }
            }
        }
    }

    @Override
    public int getCount() {
        if (mViews.size() > 1) {
            return Integer.MAX_VALUE;
        }
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = mViews.get(position % mViews.size());
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
    }
}
