package com.coding.frame.di.module;

import com.coding.frame.app.App;
import com.coding.frame.model.DataManager;
import com.coding.frame.model.http.HttpHelper;
import com.coding.frame.model.http.RetrofitHelper;
import com.coding.frame.model.prefs.ImplPreferencesHelper;
import com.coding.frame.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/03/20
 * desc   :
 * version: 1.0
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

//    @Provides
//    @Singleton
//    DBHelper provideDBHelper(RealmHelper realmHelper) {
//        return realmHelper;
//    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper,preferencesHelper);
    }
}

