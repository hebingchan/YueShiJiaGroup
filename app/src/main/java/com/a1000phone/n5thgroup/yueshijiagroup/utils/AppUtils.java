package com.a1000phone.n5thgroup.yueshijiagroup.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by hebin on 2016/12/29.
 */

public class AppUtils {
    public static final String TOKEN = "749a036dc06ae8b3a120848995a9f306";

    public static int dp2Px(float value, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                context.getResources().getDisplayMetrics());
    }
}
