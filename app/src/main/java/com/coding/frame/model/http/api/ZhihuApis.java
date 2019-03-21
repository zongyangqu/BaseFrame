package com.coding.frame.model.http.api;

import com.coding.frame.model.bean.DailyListBean;
import com.coding.frame.model.bean.WelcomeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/03/20
 * desc   :
 * version: 1.0
 */
public interface ZhihuApis {

    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 启动界面图片
     */
    @GET("start-image/{res}")
    Flowable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    /**
     * 最新日报
     */
    @GET("news/latest")
    Flowable<DailyListBean> getDailyList();


}

