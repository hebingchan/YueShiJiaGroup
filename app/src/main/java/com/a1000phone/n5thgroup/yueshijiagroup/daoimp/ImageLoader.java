package com.a1000phone.n5thgroup.yueshijiagroup.daoimp;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.a1000phone.n5thgroup.yueshijiagroup.dao.IImageLoader;
import com.bumptech.glide.Glide;

/**
 * Created by hebin on 2016/12/29.
 */

public class ImageLoader implements IImageLoader {

    @Override
    public void loadImage(final Context context, @DrawableRes final int placeHold, final String url, final ImageView imageView) {
        Glide.with(context).load(url).placeholder(placeHold).into(imageView);
    }
}
