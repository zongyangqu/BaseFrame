package com.coding.frame.di.component;

import com.coding.frame.app.App;
import com.coding.frame.di.module.AppModule;
import com.coding.frame.di.module.HttpModule;
import com.coding.frame.model.DataManager;
import com.coding.frame.model.http.RetrofitHelper;
import com.coding.frame.model.prefs.ImplPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/03/20
 * desc   :
 * version: 1.0
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    //RealmHelper realmHelper();    //提供数据库帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
