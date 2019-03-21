package com.coding.frame.base.contract.main;

import com.coding.frame.base.BasePresenter;
import com.coding.frame.base.BaseView;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/03/20
 * desc   :
 * version: 1.0
 */
public interface MainContract {

    interface View extends BaseView {

        void showUpdateDialog(String versionContent);

        void startDownloadService();
    }

    interface  Presenter extends BasePresenter<View> {

        void checkVersion(String currentVersion);

        void checkPermissions(RxPermissions rxPermissions);

        void setNightModeState(boolean b);

        void setCurrentItem(int index);

        int getCurrentItem();

        void setVersionPoint(boolean b);

        boolean getVersionPoint();
    }
}
