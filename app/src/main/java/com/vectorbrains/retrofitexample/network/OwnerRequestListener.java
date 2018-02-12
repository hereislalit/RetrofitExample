package com.vectorbrains.retrofitexample.network;

import com.vectorbrains.retrofitexample.model.Owner;

import java.util.List;

public interface OwnerRequestListener {
    void onSuccess(List<Owner> ownerList);

    void onError(String error);
}
