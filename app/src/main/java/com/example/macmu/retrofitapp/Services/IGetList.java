package com.example.macmu.retrofitapp.Services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IGetList
{
    @GET("/list/getList/{id}")
    public Call<List<Integer>> getPostWithID(@Path("id") int id);
}
