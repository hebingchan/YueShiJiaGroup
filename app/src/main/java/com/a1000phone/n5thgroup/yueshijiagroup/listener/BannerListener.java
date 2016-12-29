package com.a1000phone.n5thgroup.yueshijiagroup.listener;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

/**
 * Created by hebin on 2016/12/29.
 */

public class BannerListener implements ViewPager.OnPageChangeListener {
    private RadioGroup mDotGroup;

    public BannerListener() {
    }

    public BannerListener(final RadioGroup dotGroup) {
        mDotGroup = dotGroup;
    }

    @Override
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mDotGroup != null) {
            RadioButton radioButton = (RadioButton) mDotGroup.getChildAt(position % mDotGroup.getChildCount());
            radioButton.setChecked(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(final int state) {

    }
}
