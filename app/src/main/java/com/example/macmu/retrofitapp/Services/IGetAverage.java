package com.example.macmu.retrofitapp.Services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IGetAverage
{
    @GET("/average/{firstNameIds}")
    public Call<Float> getPostWithID(@Path("firstNameIds") String firstNameIds);
}
