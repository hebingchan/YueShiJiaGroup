package com.a1000phone.n5thgroup.yueshijiagroup.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hebin on 2016/12/29.
 */

public class BannerAdapter extends PagerAdapter {
    private List<View> mViews;

    public BannerAdapter(final List<View> views) {
        mViews = views;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return object == view;
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
