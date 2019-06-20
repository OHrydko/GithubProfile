package com.example.mvpexample;

import com.example.mvpexample.model.Github;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("users/{name}")
    Observable<Github> getProfile(@Path("name") String name);
}
