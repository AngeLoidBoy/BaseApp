package com.angeloid.netlibrary;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Angeloid
 * email:angeloidYun@gmail.com
 * wechat:flydexin
 */
public class RetrofitClient {
    /**
     * 网络请求基础URL
     */
    public static String API_SERVER_BASE_URL = "http://www.baidu.com/";

    public static Retrofit mRetrofit;

    /**
     * 获取RetrofitClient
     * @return retrofitClient对象
     */
    public static Retrofit create(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(API_SERVER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpUtils.getUnsafeHttpClient())
                    .build();
        }
        return mRetrofit;
    }
}
