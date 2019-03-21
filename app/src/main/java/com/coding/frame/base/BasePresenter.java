package com.coding.frame.base;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/03/20
 * desc   : presenter基类
 * version: 1.0
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}