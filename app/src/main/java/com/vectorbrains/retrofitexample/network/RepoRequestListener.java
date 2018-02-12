package com.vectorbrains.retrofitexample.network;

import com.vectorbrains.retrofitexample.model.Repo;

import java.util.List;

public interface RepoRequestListener {
    void onSuccess(List<Repo> repoList);

    void onError(String error);
}
