package com.yrj520.pfapp.ymjg.UI.view.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.progressHUD.KProgressHUD;



public class BaseFragment extends Fragment {
    private KProgressHUD progressHUD;

    /**
     * Description:任意页面显示进度框
     *
     * @param text 提示信息
     */
    public void showLoading(Context context, String text) {
        onCreateDialog(context, text);
    }

    /**
     * Description: 全屏变暗的dialog
     *
     * @param text 提示信息
     */
    public void showLoading1(Context context, String text) {
        if (progressHUD == null) {
            progressHUD = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel(text)
                    .setDimAmount(0.5f);
        }
        progressHUD.show();
    }

    /**
     * Description:关闭进度框
     */
    public void closeLoading() {
        if (progressHUD != null)
            progressHUD.dismiss();
    }

    protected void onCreateDialog(Context context, String text) {
        if (progressHUD == null) {
            if (StringUtils.isEmpty(text)) {
                progressHUD = KProgressHUD.create(context)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setCancellable(true);
            } else {
                progressHUD = KProgressHUD.create(context)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel(text)
                        .setCancellable(true);
            }
        }
        progressHUD.show();
    }

}
