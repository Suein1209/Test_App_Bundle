package com.suein.test.appbundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UnsplashService {
    @GET("/list")
    Call<List<Photo>> getList();
}
