package com.vectorbrains.retrofitexample.network.retrofitinterface;

import com.vectorbrains.retrofitexample.model.Owner;
import com.vectorbrains.retrofitexample.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApiForGithub {

    @GET("users")
    Call<List<Owner>> getUserList();

    @GET("users/{user}")
    Call<List<Owner>> getUserListWithName(@Path("user") String userName);

    @GET("users/{user}/repos")
    Call<List<Repo>> getRopoListWithUserName(@Path("user") String user);
}
