package com.coding.frame.di.module;

import android.util.Log;

import com.coding.frame.BuildConfig;
import com.coding.frame.app.Constants;
import com.coding.frame.di.qualifier.ZhihuUrl;
import com.coding.frame.model.http.api.ZhihuApis;
import com.coding.frame.util.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/03/20
 * desc   :
 * version: 1.0
 */
@Module
public class HttpModule {

    static final String TAG = "Api.ApiClient";

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    @ZhihuUrl
    Retrofit provideZhihuRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ZhihuApis.HOST);
    }
//
//    @Singleton
//    @Provides
//    @WechatUrl
//    Retrofit provideWechatRetrofit(Retrofit.Builder builder, OkHttpClient client) {
//        return createRetrofit(builder, client, WeChatApis.HOST);
//    }
//
//    @Singleton
//    @Provides
//    @GankUrl
//    Retrofit provideGankRetrofit(Retrofit.Builder builder, OkHttpClient client) {
//        return createRetrofit(builder, client, GankApis.HOST);
//    }
//
//    @Singleton
//    @Provides
//    @GoldUrl
//    Retrofit provideGoldRetrofit(Retrofit.Builder builder, OkHttpClient client) {
//        return createRetrofit(builder, client, GoldApis.HOST);
//    }
//
//    @Singleton
//    @Provides
//    @VtexUrl
//    Retrofit provideVtexRetrofit(Retrofit.Builder builder, OkHttpClient client) {
//        return createRetrofit(builder, client, VtexApis.HOST);
//    }
//
//    @Singleton
//    @Provides
//    @MyUrl
//    Retrofit provideMyRetrofit(Retrofit.Builder builder, OkHttpClient client) {
//        return createRetrofit(builder, client, MyApis.HOST);
//    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };

        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        builder.interceptors().add(new HttpLoggingInterceptor(new DebugLogger()).setLevel(HttpLoggingInterceptor.Level.BODY));
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();

    }

    @Singleton
    @Provides
    ZhihuApis provideZhihuService(@ZhihuUrl Retrofit retrofit) {
        return retrofit.create(ZhihuApis.class);
    }
//
//    @Singleton
//    @Provides
//    GankApis provideGankService(@GankUrl  Retrofit retrofit) {
//        return retrofit.create(GankApis.class);
//    }
//
//    @Singleton
//    @Provides
//    WeChatApis provideWechatService(@WechatUrl Retrofit retrofit) {
//        return retrofit.create(WeChatApis.class);
//    }
//
//    @Singleton
//    @Provides
//    GoldApis provideGoldService(@GoldUrl Retrofit retrofit) {
//        return retrofit.create(GoldApis.class);
//    }
//
//    @Singleton
//    @Provides
//    VtexApis provideVtexService(@VtexUrl Retrofit retrofit) {
//        return retrofit.create(VtexApis.class);
//    }
//
//    @Singleton
//    @Provides
//    MyApis provideMyService(@MyUrl Retrofit retrofit) {
//        return retrofit.create(MyApis.class);
//    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static class DebugLogger implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            Log.d(TAG, message);
        }
    }
}

