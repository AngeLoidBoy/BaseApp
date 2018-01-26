package com.angeloid.baseapplication.net;

import com.angeloid.baseapplication.bean.CategoryBean;
import com.angeloid.baseapplication.bean.base.HttpResponse;
import com.angeloid.baseapplication.bean.request.RequestBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author yunjw
 *         Created at 2018/1/26.
 *         Time is 9:44 now.
 *         (#^.^#)
 */

public interface RequestApi {
    @POST("category/list")
    Observable<HttpResponse<List<CategoryBean>>> getApp(@Body RequestBean requestBean);
}
