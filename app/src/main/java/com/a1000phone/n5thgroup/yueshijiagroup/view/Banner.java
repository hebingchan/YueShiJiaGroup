package com.a1000phone.n5thgroup.yueshijiagroup.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.a1000phone.n5thgroup.yueshijiagroup.utils.AppUtils;

/**
 * Created by hebin on 2016/12/29.
 */

public class Banner extends ViewPager {
    private Boolean mAutoScroll;
    private int mDuration;
    private Handler mHandler;
    private int mActivedPadding;
    private boolean mIsActived;
    private boolean mIsUserTouch;

    public Banner(final Context context) {
        this(context, null);
    }

    public Banner(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mHandler = new Handler();
        mDuration = 3000;
        mAutoScroll = false;
        mIsActived = false;
        mActivedPadding = AppUtils.dp2Px(10, context);
        mIsUserTouch = false;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mIsUserTouch = true;
                if (mIsActived = isActiveArea((int) ev.getX())) {
                    requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsActived) {
                    requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                mIsActived = false;
                mIsUserTouch = false;
                requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void setAutoScroll(boolean autoScroll) {
        if (!mAutoScroll) {
            mAutoScroll = autoScroll;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mAutoScroll) {
                        if (!mIsUserTouch) {
                            setCurrentItem(getCurrentItem() + 1);
                        }
                        mHandler.postDelayed(this, mDuration);
                    }
                }
            }, mDuration);
        }
    }

    private boolean isActiveArea(int x) {
        if (x >= getLeft() + getPaddingLeft() + mActivedPadding
                && x <= getRight() - getPaddingRight() - mActivedPadding) {
            return true;
        }
        return false;
    }
}
