package com.example.onlineshopping;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface orderEndpoint {
    @POST("posts")
  Call<order> neworder(@Body order order);
}
