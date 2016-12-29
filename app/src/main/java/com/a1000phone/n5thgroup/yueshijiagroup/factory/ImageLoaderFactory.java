package com.a1000phone.n5thgroup.yueshijiagroup.factory;

import com.a1000phone.n5thgroup.yueshijiagroup.dao.IImageLoader;
import com.a1000phone.n5thgroup.yueshijiagroup.daoimp.ImageLoader;

/**
 * Created by hebin on 2016/12/29.
 */

public class ImageLoaderFactory {

    public static IImageLoader getImageLoader() {
        return new ImageLoader();
    }
}
