package com.a1000phone.n5thgroup.yueshijiagroup.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.a1000phone.n5thgroup.yueshijiagroup.R;
import com.a1000phone.n5thgroup.yueshijiagroup.base.BaseActivity;
import com.a1000phone.n5thgroup.yueshijiagroup.view.ExpandableHeaderView;
import com.a1000phone.n5thgroup.yueshijiagroup.view.MultiTypeImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.logImageView)
    MultiTypeImageView mLogImageView;
    @InjectView(R.id.logHintTV)
    TextView mLogHintTV;
    @InjectView(R.id.mineViewAll)
    TextView mMineViewAll;
    @InjectView(R.id.paymentBtn)
    TextView mPaymentBtn;
    @InjectView(R.id.giftCardExchangeBtn)
    FrameLayout mGiftCardExchangeBtn;
    @InjectView(R.id.myFavorite)
    FrameLayout mMyFavorite;
    @InjectView(R.id.myActivity)
    FrameLayout mMyActivity;
    @InjectView(R.id.inviteFriend)
    FrameLayout mInviteFriend;
    @InjectView(R.id.serviceCallTitle)
    TextView mServiceCallTitle;
    @InjectView(R.id.serviceCallContent)
    TextView mServiceCallContent;
    @InjectView(R.id.serviceCall)
    FrameLayout mServiceCall;
    @InjectView(R.id.scrollView)
    ExpandableHeaderView mScrollView;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_mine);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.logImageView, R.id.mineViewAll, R.id.paymentBtn, R.id.giftCardExchangeBtn, R.id.myFavorite, R.id.myActivity, R.id.inviteFriend})
    public void onClick(View view) {
        Log.e("onClick", "onClick()");
        switch (view.getId()) {
            case R.id.logImageView:
                break;
            case R.id.mineViewAll:
                break;
            case R.id.paymentBtn:
                break;
            case R.id.giftCardExchangeBtn:
                break;
            case R.id.myFavorite:
                break;
            case R.id.myActivity:
                break;
            case R.id.inviteFriend:
                break;
        }
    }
}
