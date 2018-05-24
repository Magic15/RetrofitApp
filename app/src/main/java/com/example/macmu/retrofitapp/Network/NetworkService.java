package com.example.macmu.retrofitapp.Network;

import com.example.macmu.retrofitapp.Services.IGetList;
import com.example.macmu.retrofitapp.Services.IGetAverage;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService
{
    private static NetworkService mInstance;
   // private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
   private static final String BASE_URL = "http://172.23.176.19:8080/";

    private Retrofit mRetrofit;

    private NetworkService()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public IGetList getJSONApi()
    {
        return mRetrofit.create(IGetList.class);
    }
    public IGetAverage getLocalhostApi() { return mRetrofit.create(IGetAverage.class); }
}