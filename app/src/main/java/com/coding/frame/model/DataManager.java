package com.coding.frame.model;

import com.coding.frame.model.bean.DailyListBean;
import com.coding.frame.model.bean.WelcomeBean;
import com.coding.frame.model.http.HttpHelper;
import com.coding.frame.model.prefs.PreferencesHelper;

import java.util.List;

import io.reactivex.Flowable;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/03/20
 * desc   :
 * version: 1.0
 */
public class DataManager implements HttpHelper, PreferencesHelper {

    HttpHelper mHttpHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public boolean getNightModeState() {
        return mPreferencesHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean state) {
        mPreferencesHelper.setNightModeState(state);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferencesHelper.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean state) {
        mPreferencesHelper.setNoImageState(state);
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferencesHelper.getAutoCacheState();
    }

    @Override
    public void setAutoCacheState(boolean state) {
        mPreferencesHelper.setAutoCacheState(state);
    }

    @Override
    public int getCurrentItem() {
        return mPreferencesHelper.getCurrentItem();
    }

    @Override
    public void setCurrentItem(int item) {
        mPreferencesHelper.setCurrentItem(item);
    }

    @Override
    public boolean getLikePoint() {
        return mPreferencesHelper.getLikePoint();
    }

    @Override
    public void setLikePoint(boolean isFirst) {
        mPreferencesHelper.setLikePoint(isFirst);
    }

    @Override
    public boolean getVersionPoint() {
        return mPreferencesHelper.getVersionPoint();
    }

    @Override
    public void setVersionPoint(boolean isFirst) {
        mPreferencesHelper.setVersionPoint(isFirst);
    }

    @Override
    public boolean getManagerPoint() {
        return mPreferencesHelper.getManagerPoint();
    }

    @Override
    public void setManagerPoint(boolean isFirst) {
        mPreferencesHelper.setManagerPoint(isFirst);
    }


    @Override
    public Flowable<DailyListBean> fetchDailyListInfo() {
        return mHttpHelper.fetchDailyListInfo();
    }


    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mHttpHelper.fetchWelcomeInfo(res);
    }


}

