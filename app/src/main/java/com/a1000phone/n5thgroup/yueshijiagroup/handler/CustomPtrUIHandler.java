package com.a1000phone.n5thgroup.yueshijiagroup.handler;

import android.util.Log;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by hebin on 2016/12/30.
 */

public class CustomPtrUIHandler implements PtrUIHandler {

    @Override
    public void onUIReset(final PtrFrameLayout frame) {
        Log.e("CustomPtrUIHandler", "onUIReset");
    }

    @Override
    public void onUIRefreshPrepare(final PtrFrameLayout frame) {
        Log.e("CustomPtrUIHandler", "onUIRefreshPrepare");
    }

    @Override
    public void onUIRefreshBegin(final PtrFrameLayout frame) {
        Log.e("CustomPtrUIHandler", "onUIRefreshBegin");
    }

    @Override
    public void onUIRefreshComplete(final PtrFrameLayout frame) {
        Log.e("CustomPtrUIHandler", "onUIRefreshComplete");
    }

    @Override
    public void onUIPositionChange(final PtrFrameLayout frame, final boolean isUnderTouch, final byte status, final PtrIndicator ptrIndicator) {
        Log.e("CustomPtrUIHandler", "onUIPositionChange");
    }
}
