package com.yundadialog;

import com.example.yunda_app_2016_3_18.R;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class ShareBottomDialog extends BottomBaseDialog<ShareBottomDialog> {
    private LinearLayout ll_wechat_friend_circle;
    private LinearLayout ll_wechat_friend;
    private LinearLayout ll_qq;
    private LinearLayout ll_sms;

    public ShareBottomDialog(Context context, View animateView) {
        super(context, animateView);
    }

    public ShareBottomDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        showAnim(new FlipVerticalSwingEnter());
        dismissAnim(null);
        View inflate = View.inflate(context, R.layout.dialog_share, null);
        ll_wechat_friend_circle = ViewFindUtils.find(inflate, R.id.ll_wechat_friend_circle);
        ll_wechat_friend = ViewFindUtils.find(inflate, R.id.ll_wechat_friend);
        ll_qq = ViewFindUtils.find(inflate, R.id.ll_qq);
        ll_sms = ViewFindUtils.find(inflate, R.id.ll_sms);

        return inflate;
    }

    @Override
    public void setUiBeforShow() {

        ll_wechat_friend_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(context, "朋友圈");
                dismiss();
            }
        });
        ll_wechat_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(context, "微信");
                dismiss();
            }
        });
        ll_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(context, "QQ");
                dismiss();
            }
        });
        ll_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(context, "短信");
                dismiss();
            }
        });
    }
}
