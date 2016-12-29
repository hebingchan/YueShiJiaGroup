package com.a1000phone.n5thgroup.yueshijiagroup.dao;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * Created by hebin on 2016/12/29.
 */

public interface IImageLoader {

    void loadImage(Context context, @DrawableRes int placeHold, String url, ImageView imageView);
}
