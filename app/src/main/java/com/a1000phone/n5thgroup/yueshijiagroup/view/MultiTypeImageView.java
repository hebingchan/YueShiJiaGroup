package com.a1000phone.n5thgroup.yueshijiagroup.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ImageView;


import com.a1000phone.n5thgroup.yueshijiagroup.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by hebin on 2016/11/16.
 */

public class MultiTypeImageView extends ImageView {

    public static final int DEF_TYPE = 0;
    public static final int NORMAL = 0;
    public static final int ROUND = 1;
    public static final int ROUND_SQUARE = 3;
    public static final int DEF_CON_RADIUS = 30;
    public static final int POLYGON = 4;
    public static final int DEF_SIDE_NUM = 5;
    public static final int GEAR = 5;
    public static final int DEF_SAWTOOTH_NUM = 12;
    public static final int DEF_SAWTOOTH_HEIGHT = 30;
    private int mImageViewType;
    private int mHeight;
    private int mWidth;
    private Paint mPaint;
    private float mConnerRadius;
    private int mSideNum;
    private int mSawTootchNum;
    private int mSawToothHeight;

    public MultiTypeImageView(Context context) {
        this(context, null);
    }

    public MultiTypeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiTypeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMultiTypeImageView(attrs);
    }

    private void initMultiTypeImageView(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MultiTypeImageView);
        mImageViewType = typedArray.getInt(R.styleable.MultiTypeImageView_style, DEF_TYPE);
        mConnerRadius = typedArray.getDimension(R.styleable.MultiTypeImageView_connerRadius, DEF_CON_RADIUS);
        mSideNum = typedArray.getInt(R.styleable.MultiTypeImageView_sideNum, DEF_SIDE_NUM);
        mSawTootchNum = typedArray.getInt(R.styleable.MultiTypeImageView_sawToothNum, DEF_SAWTOOTH_NUM);
        mSawToothHeight = typedArray.getDimensionPixelSize(R.styleable.MultiTypeImageView_sawToothHeight, DEF_SAWTOOTH_HEIGHT);
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int min = Math.min(mHeight - getPaddingTop() - getPaddingBottom()
                    , mWidth - getPaddingLeft() - getPaddingRight());
            switch (mImageViewType) {
                case ROUND:
                    Bitmap roundBitmap = getRoundBitmap(min);
                    drawBitmap(canvas, roundBitmap);
                    break;
                case ROUND_SQUARE:
                    Bitmap roundSquareBitmap = getRoundSquareBitmap(min);
                    drawBitmap(canvas, roundSquareBitmap);
                    break;
                case POLYGON:
                    Bitmap polygonBitmap = getPolygonBitmap(min);
                    drawBitmap(canvas, polygonBitmap);
                    break;
                case GEAR:
                    Bitmap gearBitmap = getGearBitmap(min);
                    drawBitmap(canvas, gearBitmap);
                    break;
                case NORMAL:
                    super.onDraw(canvas);
                    break;
            }
        }
    }

    private Bitmap getRoundRectBitmap(final int min) {
        return null;
    }

    private void drawBitmap(final Canvas canvas, final Bitmap dst) {
        canvas.drawBitmap(dst, getPaddingLeft(), getPaddingTop(), mPaint);
    }

    private Bitmap getGearBitmap(final int min) {
        Bitmap src = getSrcBitmap(min);
        Bitmap dst = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvasDst = new Canvas(dst);
        Path path = new Path();
        float cx = mWidth / 2 - getPaddingLeft();
        float cy = mHeight / 2 - getPaddingTop();
        float radius1 = min / 2;
        float radius2 = (min - mSawToothHeight) / 2;
        float deltaRadian = (float) (Math.PI * 2 / (mSawTootchNum * 2));
        path.moveTo(cx, cy - radius1);
        for (int i = 1; i < mSawTootchNum * 2; i++) {
            float radius = 0;
            if (i % 2 == 0) {
                radius = radius1;
            } else {
                radius = radius2;
            }
            float tx = (float) (Math.sin(i * deltaRadian) * radius);
            float ty = (float) (Math.cos(i * deltaRadian) * radius);
            path.lineTo(cx + tx, cy - ty);
        }
        path.close();
        canvasDst.drawPath(path, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvasDst.drawBitmap(src, 0, 0, mPaint);
        mPaint.setXfermode(null);
        return dst;
    }

    private Bitmap getPolygonBitmap(final int min) {
        Bitmap src = getSrcBitmap(min);
        Bitmap dst = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvasDst = new Canvas(dst);
        Path path = new Path();
        float deltaRadian = (float) (2 * Math.PI / mSideNum);
        int cx = mWidth / 2 - getPaddingLeft();
        int cy = mHeight / 2 - getPaddingTop();
        float radius = min / 2;
        path.moveTo(cx, cy - radius);
        for (int i = 1; i < mSideNum; i++) {
            float ty = (float) (Math.cos(deltaRadian * i) * radius);
            float tx = (float) (Math.sin(deltaRadian * i) * radius);
            path.lineTo(cx + tx, cy - ty);
        }
        path.close();
        canvasDst.drawPath(path, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvasDst.drawBitmap(src, 0, 0, mPaint);
        mPaint.setXfermode(null);
        return dst;
    }

    private Bitmap getRoundSquareBitmap(final int min) {
        Bitmap src = getSrcBitmap(min);
        Bitmap dst = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvasDst = new Canvas(dst);
        Path path = new Path();
        RectF bounds = new RectF(mWidth / 2 - getPaddingLeft() - min / 2, mHeight / 2 - getPaddingTop() - min / 2
                , mWidth / 2 - getPaddingLeft() + min / 2, mHeight / 2 - getPaddingTop() + min / 2);
        path.addRoundRect(bounds, mConnerRadius, mConnerRadius, Path.Direction.CCW);
        canvasDst.drawPath(path, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvasDst.drawBitmap(src, 0, 0, mPaint);
        mPaint.setXfermode(null);
        return dst;
    }

    private Bitmap getRoundBitmap(final int min) {
        Bitmap src = getSrcBitmap(min);
        Bitmap dst = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvasDst = new Canvas(dst);
        canvasDst.drawCircle((mWidth >> 1) - getPaddingLeft(), (mHeight >> 1) - getPaddingTop(), min >> 1, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvasDst.drawBitmap(src, 0, 0, mPaint);
        mPaint.setXfermode(null);
        return dst;
    }

    @NonNull
    private Bitmap getSrcBitmap(final int min) {
        Rect drawableBounds = getDrawableBounds();
        getDrawable().setBounds(drawableBounds);
        Bitmap src = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvasSrc = new Canvas(src);
        int leftOffset = drawableBounds.left + drawableBounds.width() / 2 - min / 2;
        leftOffset = (leftOffset < 0 ? 0 : leftOffset);
        int topOffset = drawableBounds.top + drawableBounds.height() / 2 - min / 2;
        topOffset = (topOffset < 0 ? 0 : topOffset);
        canvasSrc.translate(-getPaddingLeft() + -1 * leftOffset + mWidth / 2 - min / 2
                , -getPaddingTop() + -1 * topOffset + mHeight / 2 - min / 2);
        getDrawable().draw(canvasSrc);
        return src;
    }

    public Rect getDrawableBounds() {
        int height = 0;
        int width = 0;
        Rect dBounds = getDrawable().getBounds();
        if (dBounds.width() > dBounds.height()) {
            height = Math.min(mHeight - getPaddingBottom() - getPaddingTop()
                    , mWidth - getPaddingLeft() - getPaddingRight());
            width = (int) (height * 1.0f / dBounds.height() * dBounds.width());
        } else {
            width = Math.min(mHeight - getPaddingTop() - getPaddingBottom()
                    , mWidth - getPaddingLeft() - getPaddingRight());
            height = (int) (width * 1.0f / dBounds.width() * dBounds.height());
        }
        return new Rect(0, 0, width, height);
    }

    @IntDef(value = {ROUND, ROUND_SQUARE, GEAR, POLYGON, NORMAL})
    @Retention(RetentionPolicy.SOURCE)
    private @interface ImageViewType {
    }

    public void setImageViewType(@ImageViewType int type) {
        mImageViewType = type;
    }
}
