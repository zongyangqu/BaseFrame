package com.coding.frame.base;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/03/20
 * desc   : View基类
 * version: 1.0
 */
public interface BaseView {

    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State  =======
    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
