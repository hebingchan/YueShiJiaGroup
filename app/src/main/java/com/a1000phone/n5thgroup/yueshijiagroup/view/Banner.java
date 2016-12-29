package com.a1000phone.n5thgroup.yueshijiagroup.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by hebin on 2016/12/29.
 */

public class Banner extends ViewPager {
    public static final int SCROLL_TO_NEXT = 0x112;
    private Boolean mAutoScroll;
    private int mDuration;
    private OnItemClickListener mOnItemClickListener = null;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(final Message msg) {
            switch (msg.what) {
                case SCROLL_TO_NEXT:
                    setCurrentItem(getCurrentItem() + 1);
                    mHandler.sendEmptyMessageDelayed(SCROLL_TO_NEXT, mDuration);
                    break;
            }
        }
    };

    public interface OnItemClickListener {

        void onItemClick();
    }

    public Banner(final Context context) {
        this(context, null);
    }

    public Banner(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mDuration = 3000;
        mAutoScroll = false;
        setViewPagerScroller();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (onItemClickListener != null) {
            setClickable(true);
            mOnItemClickListener = onItemClickListener;
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick();
                    }
                }
            });
        }
    }

    public void onItemClick() {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick();
        }
    }

    @Override
    public boolean dispatchTouchEvent(final MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mAutoScroll) {
                    stopAutoScroll();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mAutoScroll) {
                    startAutoScroll();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void stopAutoScroll() {
        mHandler.removeMessages(SCROLL_TO_NEXT);
    }

    private void startAutoScroll() {
        mHandler.sendEmptyMessageDelayed(SCROLL_TO_NEXT, mDuration);
    }

    public void setAutoScroll(boolean autoScroll) {
        if (!mAutoScroll) {
            mAutoScroll = autoScroll;
            startAutoScroll();
        }
    }

    private void setViewPagerScroller() {
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            Field interpolatorField = ViewPager.class.getDeclaredField("sInterpolator");
            interpolatorField.setAccessible(true);

            CustomDurationScroller customDurationScroller = new CustomDurationScroller
                    (getContext(), new AccelerateDecelerateInterpolator());
            customDurationScroller.setDuration(500);
            scrollerField.set(this, customDurationScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDuration(final int duration) {
        mDuration = duration;
    }

    private class CustomDurationScroller extends Scroller {

        private int mDuration;

        public CustomDurationScroller(Context context) {
            super(context);
        }

        public CustomDurationScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public void setDuration(int duration) {
            mDuration = duration;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }
}
