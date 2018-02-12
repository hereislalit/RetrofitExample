package com.vectorbrains.retrofitexample.network;

import com.vectorbrains.retrofitexample.appconstant.AppConstant;
import com.vectorbrains.retrofitexample.model.Owner;
import com.vectorbrains.retrofitexample.model.Repo;
import com.vectorbrains.retrofitexample.network.retrofitinterface.RetrofitApiForGithub;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAdapterForGithub {
    private RetrofitApiForGithub retrofitApi;

    public RetrofitAdapterForGithub() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppConstant.GIT_USER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitApi = retrofit.create(RetrofitApiForGithub.class);
    }

    public void runRequestToFetchUsers(final OwnerRequestListener requestListener) {
        Call<List<Owner>> ownerListCall = retrofitApi.getUserList();
        ownerListCall.enqueue(new Callback<List<Owner>>() {
            @Override
            public void onResponse(Call<List<Owner>> call, Response<List<Owner>> response) {
                requestListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Owner>> call, Throwable t) {
                requestListener.onError(t.getMessage());
            }
        });
    }

    public void runRequestToFetchRepo(String userName, final RepoRequestListener repoRequestListener) {
        Call<List<Repo>> repoListCall = retrofitApi.getRopoListWithUserName(userName);
        repoListCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                repoRequestListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                repoRequestListener.onError(t.getMessage());
            }
        });
    }

    public void runRequestToFetchUsers(String userName, final OwnerRequestListener requestListener){
        Call<List<Owner>> ownerListCall = retrofitApi.getUserListWithName(userName);
        ownerListCall.enqueue(new Callback<List<Owner>>() {
            @Override
            public void onResponse(Call<List<Owner>> call, Response<List<Owner>> response) {
                requestListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Owner>> call, Throwable t) {
                requestListener.onError(t.getMessage());
            }
        });
    }
}
