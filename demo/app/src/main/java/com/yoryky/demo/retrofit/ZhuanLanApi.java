package com.yoryky.demo.retrofit;

import com.yoryky.demo.entity.ZhuanLanAuthor;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZhuanLanApi {
    @GET("/api/columns/{user}")
    Call<ZhuanLanAuthor> getAuthor(@Path("user") String user);
}
