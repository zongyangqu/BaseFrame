package com.coding.frame.di.component;

import android.app.Activity;

import com.coding.frame.MainActivity;
import com.coding.frame.di.module.ActivityModule;
import com.coding.frame.di.scope.ActivityScope;

import dagger.Component;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/03/20
 * desc   :
 * version: 1.0
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    //void inject(WelcomeActivity welcomeActivity);

    void inject(MainActivity mainActivity);
}
