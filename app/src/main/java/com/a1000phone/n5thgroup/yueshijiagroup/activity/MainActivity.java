package com.a1000phone.n5thgroup.yueshijiagroup.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.a1000phone.n5thgroup.yueshijiagroup.R;
import com.a1000phone.n5thgroup.yueshijiagroup.base.BaseActivity;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_mine);
        ButterKnife.inject(this);
    }
}
