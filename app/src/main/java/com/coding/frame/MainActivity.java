package com.coding.frame;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.coding.frame.base.BaseActivity;
import com.coding.frame.base.contract.main.MainContract;
import com.coding.frame.presenter.main.MainPresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View{

    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;
//    @BindView(R.id.view_search)
//    MaterialSearchView mSearchView;
    @Override
    public void showUpdateDialog(String versionContent) {

    }

    @Override
    public void startDownloadService() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.checkVersion("1");

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
