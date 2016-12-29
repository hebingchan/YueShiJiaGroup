package com.a1000phone.n5thgroup.yueshijiagroup.daobiz;

import com.a1000phone.n5thgroup.yueshijiagroup.dao.IImageLoader;
import com.a1000phone.n5thgroup.yueshijiagroup.factory.ImageLoaderFactory;

/**
 * Created by hebin on 2016/12/29.
 */

public class ImageLoadManager {
    private IImageLoader mImageLoader = ImageLoaderFactory.getImageLoader();
    private volatile static ImageLoadManager sImageLoadManager = null;

    private ImageLoadManager() {}

    public static ImageLoadManager getInstance() {
        if (sImageLoadManager == null) {
            synchronized (ImageLoadManager.class) {
                if (sImageLoadManager == null) {
                    sImageLoadManager = new ImageLoadManager();
                }
                return sImageLoadManager;
            }
        }
        return sImageLoadManager;
    }

    public IImageLoader getImageLoader() {
        return mImageLoader;
    }
}
