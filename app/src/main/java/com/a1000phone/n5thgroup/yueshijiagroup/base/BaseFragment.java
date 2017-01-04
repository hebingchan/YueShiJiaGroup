package com.a1000phone.n5thgroup.yueshijiagroup.base;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hebin on 2016/12/29.
 */

public class BaseFragment extends Fragment {

    protected AppCompatActivity mActivity;

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        if (context instanceof AppCompatActivity) {
            mActivity = (AppCompatActivity) context;
        }
    }
}
