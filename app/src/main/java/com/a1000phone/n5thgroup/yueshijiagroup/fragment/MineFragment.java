package com.a1000phone.n5thgroup.yueshijiagroup.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1000phone.n5thgroup.yueshijiagroup.R;
import com.a1000phone.n5thgroup.yueshijiagroup.base.BaseFragment;

/**
 * 我的页面的Fragment
 * Created by hebin on 2016/12/29.
 */

public class MineFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_mine, container, false);
        return view;
    }
}
