package com.coding.frame.presenter.main;

import android.Manifest;

import com.coding.frame.base.RxPresenter;
import com.coding.frame.base.contract.main.MainContract;
import com.coding.frame.component.RxBus;
import com.coding.frame.model.DataManager;
import com.coding.frame.model.bean.DailyListBean;
import com.coding.frame.util.RxUtil;
import com.coding.frame.widget.CommonSubscriber;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/03/20
 * desc   :
 * version: 1.0
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
    }

    @Override
    public void checkVersion(final String currentVersion) {
        addSubscribe(mDataManager.fetchDailyListInfo()
                .compose(RxUtil.<DailyListBean>rxSchedulerHelper())
                .map(new Function<DailyListBean, DailyListBean>() {
                    @Override
                    public DailyListBean apply(DailyListBean dailyListBean) {
                        List<DailyListBean.StoriesBean> list = dailyListBean.getStories();
                        for(DailyListBean.StoriesBean item : list) {
                            //item.setReadState(mDataManager.queryNewsId(item.getId()));
                        }
                        return dailyListBean;
                    }
                })
                .subscribeWith(new CommonSubscriber<DailyListBean>(mView) {
                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        if(!dailyListBean.getStories().isEmpty()){
                            DailyListBean.StoriesBean storiesBean = dailyListBean.getStories().get(0);
                        }
                    }
                })
        );
    }

    @Override
    public void checkPermissions(RxPermissions rxPermissions) {
        addSubscribe(rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) {
                        if (granted) {
                            mView.startDownloadService();
                        } else {
                            mView.showErrorMsg("下载应用需要文件写入权限哦~");
                        }
                    }
                })
        );
    }

    @Override
    public void setNightModeState(boolean b) {
        mDataManager.setNightModeState(b);
    }

    @Override
    public void setCurrentItem(int index) {
        mDataManager.setCurrentItem(index);
    }

    @Override
    public int getCurrentItem() {
        return mDataManager.getCurrentItem();
    }

    @Override
    public void setVersionPoint(boolean b) {
        mDataManager.setVersionPoint(b);
    }

    @Override
    public boolean getVersionPoint() {
        return mDataManager.getVersionPoint();
    }

}

