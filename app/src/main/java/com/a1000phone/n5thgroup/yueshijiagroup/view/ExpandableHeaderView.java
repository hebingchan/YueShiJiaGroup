package com.a1000phone.n5thgroup.yueshijiagroup.view;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

/**
 * Created by hebin on 2016/12/30.
 */

public class ExpandableHeaderView extends NestedScrollView {
    private View mHeader;
    private View mFooter;
    private ValueAnimator mFootRestoreAnim;
    private ValueAnimator mHeadRestoreAnim;
    private AnimatorSet mRestoreAnimator;
    private float mDamping;

    public ExpandableHeaderView(final Context context) {
        super(context);
    }

    public ExpandableHeaderView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mDamping = 0.4f;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeader = findViewWithTag("Header");
        if (mHeader == null) {
            throw new IllegalArgumentException("HeaderView为空或者没有设置TAG\"Header\"");
        }
        mFooter = findViewWithTag("Footer");
        if (mFooter == null) {
            throw new IllegalArgumentException("FooterView为空或者没有设置TAG\"Footer\"");
        }
        initAnimator();
    }

    private int mDown = 0;
    private boolean mIsReachTop = false;

    @Override
    public boolean onTouchEvent(final MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (getScrollY() == 0) {
                    mDown = (int) ev.getY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsReachTop = isReachTop(ev)) {
                    onMove(ev);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mIsReachTop || mHeader.getScaleX() != 1) {
                    mIsReachTop = false;
                    restoreState();
                    return true;
                }
        }
        return super.onTouchEvent(ev);
    }

    private void restoreState() {
        if (mRestoreAnimator != null) {
            if (mRestoreAnimator.isRunning()) {
                mRestoreAnimator.cancel();
            }
            mFootRestoreAnim.setFloatValues(mFooter.getTranslationY(), 0);
            mHeadRestoreAnim.setFloatValues(mHeader.getScaleY(), 1);
            mRestoreAnimator.start();
        }
    }

    private void initAnimator() {
        mFootRestoreAnim = ValueAnimator.ofFloat(mFooter.getTranslationY(), 0);
        mFootRestoreAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                footerTranslateY(value);
            }
        });
        mHeadRestoreAnim = ValueAnimator.ofFloat(mHeader.getScaleY(), 1);
        mHeadRestoreAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mHeader.setScaleX(value);
                mHeader.setScaleY(value);
                float realHeight = mHeader.getMeasuredHeight() * value;
                realHeight -= mHeader.getMeasuredHeight();
                mHeader.setTranslationY(realHeight / 2);
            }
        });
        mRestoreAnimator = new AnimatorSet();
        mRestoreAnimator.setInterpolator(new DecelerateInterpolator(0.5f));
        mRestoreAnimator.setDuration(400);
        mRestoreAnimator.play(mFootRestoreAnim).with(mHeadRestoreAnim);
    }

    private void onMove(final MotionEvent ev) {
        int moveY = (int) (ev.getY() - mDown);
        if (mFooter != null) {
            if (mFooter.getTranslationY() + mDamping * moveY < 0) {
                footerTranslateY(0);
            } else {
                footerTranslateY(mFooter.getTranslationY() + mDamping * moveY);
            }
        }
        if (mHeader != null) {
            float scale = (mHeader.getMeasuredHeight() + mFooter.getTranslationY()) * 1.0f / mHeader.getMeasuredHeight();
            mHeader.setScaleX(scale);
            mHeader.setScaleY(scale);
            float realHeight = mHeader.getMeasuredHeight() * scale;
            realHeight -= mHeader.getMeasuredHeight();
            mHeader.setTranslationY(realHeight / 2);
        }
        mDown = (int) ev.getY();
    }

    private void footerTranslateY(final float translationY) {
        mFooter.setTranslationY(translationY);
    }

    public boolean isReachTop(MotionEvent event) {
        float moveY = event.getY() - mDown;
        return (moveY > 0 && getScrollY() == 0) || mHeader.getTranslationY() > 0;
    }
}
