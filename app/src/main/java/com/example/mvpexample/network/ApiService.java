package com.example.mvpexample.network;

import com.example.mvpexample.model.Github;
import com.example.mvpexample.model.GithubRepository;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("users/{name}")
    Observable<Github> getProfile(@Path("name") String name);

    @GET("users/{name}/repos")
    Observable<ArrayList<GithubRepository>> getRepos(@Path("name") String name);
}
